package com.nio.xul_server.impl;


import com.nio.xul_server.XulHttpServer;

/**
 * Created by hy on 2015/12/3.
 */
public interface IXulCommandHandler {
	XulHttpServer.XulHttpServerResponse execCommand(String url, XulHttpServer.XulHttpServerHandler serverHandler, XulHttpServer.XulHttpServerRequest request);
}
