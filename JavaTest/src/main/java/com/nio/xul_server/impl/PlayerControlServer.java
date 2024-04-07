package com.nio.xul_server.impl;

import com.nio.xul_server.XulHttpServer;

import java.io.IOException;
import java.nio.channels.SocketChannel;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * date：2024/4/1
 * description：播放控制服端
 */
public class PlayerControlServer extends XulHttpServer {
    public static XulHttpServerResponse PENDING_RESPONSE = new XulHttpServerResponse(null);
    private static XulHttpServer _debugServer;
    private static ArrayList<IXulCommandHandler> _userHandlers;

    public PlayerControlServer(int port) {
        super(port);
    }

    public PlayerControlServer(String addr, int port) {
        super(addr, port);
    }

    public static void startUp(int servPort) {
        if (_debugServer != null) {
            return;
        }
        _debugServer = new PlayerControlServer(servPort);
    }

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
        return new CncrPlayerApiHandler(server, socketChannel);
    }

    private static class CncrPlayerApiHandler extends XulHttpServerHandler{
        private static final String TAG = CncrPlayerApiHandler.class.getSimpleName();
        private volatile SimpleDateFormat _dateTimeFormat;

        public CncrPlayerApiHandler(XulHttpServer server, SocketChannel socketChannel) {
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
                        .addHeader("Server", "Starcor Player Server/1.0");
            }
            return response;
        }

        @Override
        protected void handleHttpRequest(XulHttpServerRequest request) throws IOException {
            String method = request.method;
            String contentType = request.getHeader("content-type");
            if(method.equalsIgnoreCase("POST")){
                byte[] body = request.body;
                XulHttpServerResponse response = null;
                BodyInfos bodyInfos = new BodyInfos();
                if("application/x-www-form-urlencoded".equals(contentType)){
                    String bodyContent = new String(body);
                    System.out.println("bodyContent = "+ bodyContent);
                    if(!bodyContent.isEmpty()){
                        String[] contents = bodyContent.split("&");
                        for (String content : contents) {
                            bodyInfos.putField(content);
                        }
                    }
                    response = getResponse(request);
                }
                if (response != null) {
                    response.send();
                }else{
                    getResponse(request)
                            .setStatus(501)
                            .setMessage("Command is unSupport")
                            .send();
                }
                return;
            }
            //else if(method.equalsIgnoreCase("GET")){
            //    //TODO GET的请求未实现，暂不需要
            //    response = unsupportedCommand(request);
            //}
            this.getResponse(request)
                    .setStatus(404)
                    .setMessage("Command Not Found")
                    .send();
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
                    .setMessage("Command API Not implemented");
        }
    }

    public static class BodyInfos {
        private final HashMap<String,Object> body = new HashMap<>();

        public void putField(String paire){
            String[] split = paire.split("=");
            if(split.length == 2){
                body.put(split[0],split[1]);
            }
        }

        public void putField(String key,Object obj){
            body.put(key,obj);
        }

        public Object getField(String key){
            return body.get(key);
        }

        @Override
        public String toString() {
            return "BodyInfos{" +
                    "body=" + body +
                    '}';
        }
    }
}
