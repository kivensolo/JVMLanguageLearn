package com.nio.xul_server.impl;

import com.nio.xul_server.XulHttpRequest;
import com.nio.xul_server.XulHttpServer;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.channels.SocketChannel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by hy on 2015/11/30.
 */
public class XulDebugServer extends XulHttpServer {

    public static XulHttpServerResponse PENDING_RESPONSE = new XulHttpServerResponse(null);
    private static XulHttpServer _debugServer;
    private static ArrayList<IXulCommandHandler> _userHandlers;

    private XulDebugServer(int servPort) {
        super(servPort);
    }

    public static void startUp() {
        startUp(55550);
    }

    public static void startUp(int servPort) {
        if (_debugServer != null) {
            return;
        }
        _debugServer = new XulDebugServer(servPort);
        //...... do other function logic
    }

    /**
     * 注册外部调试命令处理器
     *
     * @param debugCommandHandler
     * @return
     */
    public static synchronized boolean registerCommandHandler(IXulCommandHandler debugCommandHandler) {
        if (_userHandlers == null) {
            _userHandlers = new ArrayList<IXulCommandHandler>();
        }

        if (_userHandlers.contains(debugCommandHandler)) {
            return false;
        }
        _userHandlers.add(debugCommandHandler);
        return true;
    }

    @Override
    protected XulHttpServerHandler createHandler(XulHttpServer server, SocketChannel socketChannel) {
        return new XulDebugApiHandler(server, socketChannel);
    }

    private static class XulDebugApiHandler extends XulHttpServerHandler {

        private static final String TAG = XulDebugApiHandler.class.getSimpleName();
        private volatile SimpleDateFormat _dateTimeFormat;

        public XulDebugApiHandler(XulHttpServer server, SocketChannel socketChannel) {
            super(server, socketChannel);
        }

        @Override
        public XulHttpServerResponse getResponse(XulHttpServerRequest httpRequest) {
            XulHttpServerResponse response = super.getResponse(httpRequest);
            synchronized (this) {
                if (_dateTimeFormat == null) {
                    _dateTimeFormat = new SimpleDateFormat("ccc, dd MMM yyyy HH:mm:ss 'GMT'", Locale.US);
                    _dateTimeFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
                }
                response.addHeader("Date", _dateTimeFormat.format(new Date()))
                        .addHeader("Server", "Xul Debug Server/1.0");
            }
            return response;
        }

        @Override
        protected void handleHttpRequest(XulHttpServerRequest request) throws IOException {
            String path = request.path;
            if (path != null && path.startsWith("/api/")) {
                XulHttpServerResponse response = null;
                //判断path,实现response的返回
                if ("/api/list-pages".equals(path)) {
                    response = listPages(request);
                }
				//............也可以实现其他功能
				else if (path.equals("/api/get-logcat")) {
                    response = responseCommandOutput(request, "logcat -v time");
                } else if (path.equals("/api/dump-heap")) {
                    response = getResponse(request);
                    final XulHttpRequest xulHttpRequest = request.makeCloneNoQueryString();
                    xulHttpRequest.path += "/app_dump.hprof";
                    response.setStatus(302)
                            .addHeader("Location", xulHttpRequest.toString());
                }else {
                    response = unsupportedCommand(request);
                }

                if (response == PENDING_RESPONSE) {
                    return;
                }

                if (response != null) {
                    response.send();
                    return;
                }
            }
            super.handleHttpRequest(request);
        }

		private XulHttpServerResponse unsupportedCommand(XulHttpServerRequest request) {
			ArrayList<IXulCommandHandler> userHandlers = _userHandlers;
			if (userHandlers != null) {
				for (int i = 0, userHandlersSize = userHandlers.size(); i < userHandlersSize; i++) {
					IXulCommandHandler handler = userHandlers.get(i);
					try {
						XulHttpServerResponse response = handler.execCommand(request.path, this, request);
						if (response != null) {
							return response;
						}
					} catch (Exception e) {
						System.out.println(e);
					}
				}
			}
			return getResponse(request)
					.setStatus(501)
					.setMessage("Debug API Not implemented");
		}

        private void responseFile(XulHttpServerRequest request, final File fileName, boolean autoDelete) throws IOException {
            if (fileName == null || !fileName.exists() || !fileName.canRead()) {
                super.handleHttpRequest(request);
                return;
            }

            final FileInputStream fileInputStream = new FileInputStream(fileName);
            final XulHttpServerResponse response = getResponse(request);
            response.addHeader("Content-Type", "application/oct-stream")
                    .writeStream(fileInputStream);

            if (autoDelete) {
                response.setCleanUp(new Runnable() {
                    @Override
                    public void run() {
                        fileName.delete();
                    }
                });
            }
            response.send();
        }

        private XulHttpServerResponse responseCommandOutput(XulHttpServerRequest request, String command) {
            XulHttpServerResponse response;
            response = getResponse(request)
                    .addHeader("Content-Type", "text/plain");
            try {
                final Process exec = Runtime.getRuntime().exec(command);
                final InputStream inputStream = exec.getInputStream();
                response.writeStream(inputStream)
                        .addHeader("Transfer-Encoding", "chunked")
                        .setCleanUp(new Runnable() {
                            @Override
                            public void run() {
                                exec.destroy();
                            }
                        });
            } catch (Exception e) {
                e.printStackTrace(new PrintStream(response.getBodyStream()));
            }
            return response;
        }



        private XulHttpServerResponse listPages(XulHttpServerRequest request) {
            XulHttpServerResponse response = getResponse(request);
            //自行判断是否完善了Response
//			if (_monitor.dumpPageList(request, response)) {
//				response.addHeader("Content-Type", "text/xml");
//			} else {
//				response.setStatus(404)
//					.cleanBody();
//			}
            return response;
        }

    }
}
