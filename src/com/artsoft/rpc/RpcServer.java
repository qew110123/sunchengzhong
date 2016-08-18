package com.artsoft.rpc;

import java.io.IOException;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.server.PropertyHandlerMapping;
import org.apache.xmlrpc.server.XmlRpcServer;
import org.apache.xmlrpc.server.XmlRpcServerConfigImpl;
import org.apache.xmlrpc.webserver.WebServer;

public class RpcServer {
	
	private WebServer webServer;
	private XmlRpcServer xmlRpcServer;
	private PropertyHandlerMapping phm;
	private XmlRpcServerConfigImpl serverConfig;
	private int port;
	private static RpcServer  rpcServer;
	
	private RpcServer(){
		
		String strLocalPort ="9923";
		init(Integer.valueOf(strLocalPort));
		try {
			//设置发布类 名称 路径
			regeditMethod("RpcInterface", com.artsoft.rpc.RpcInterface.class);
			startRpcServer();
			System.out.println("------------->Start rpcServer: "+strLocalPort+"  <---------------");
		} catch (XmlRpcException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static RpcServer getInstance(){
		rpcServer = new RpcServer();
		return rpcServer;
	}
	
	private void initServer(int port){
		this.webServer = new WebServer(port);
		this.xmlRpcServer = webServer.getXmlRpcServer();
		this.phm = new PropertyHandlerMapping ();
	}
	
	/**
	 * 初始化端口
	 * @param port
	 */
	private void init(int port){
		this.port = port;
		this.initServer(port);
	
	}
	
	/**
	 * 初始化端口
	 * @param port
	 */
	private void init(){
		this.initServer(port);
	}
	
	/**
	 * 注册执行类到rpc服务当中
	 * @param strExcuteClass
	 * @param arg1
	 * @throws XmlRpcException
	 */
	private void regeditMethod(String strExcuteClass,Class arg1 ) throws XmlRpcException{
		phm.addHandler(strExcuteClass,arg1);

		xmlRpcServer.setHandlerMapping(phm);
		serverConfig = (XmlRpcServerConfigImpl) xmlRpcServer.getConfig();
		
		serverConfig.setEnabledForExtensions(true);
		serverConfig.setContentLengthOptional(false);
	}
	
	/**
	 * 启动rpc的服务
	 * @throws IOException
	 */
	private void startRpcServer() throws IOException{
		webServer.start();
	}
	
	/**
	 * @throws IOException
	 */
	private void shutdownRpcServer() throws IOException{
		webServer.shutdown();
	}
	
	
	public static void main(String[] args){
		RpcServer.getInstance();
	}
}
