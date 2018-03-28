package com.wk.springbootapp.app.component.es.config;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Configuration
public class ESConfig {

    @Value("${spring.data.elasticsearch.cluster-name}")
    private String clusterName;

    @Value("${spring.data.elasticsearch.cluster-nodes}")
    private String clusterNodes;


    @Bean
    public TransportClient transportClient ()  {
        Settings settings = Settings.builder().put("cluster.name", clusterName).build();
        TransportClient client = new PreBuiltTransportClient(settings);
        String[] clusterNodeList = clusterNodes.split(",");
        for (String clusterNode : clusterNodeList) {
            String peerHost;
            String peerPort;
            if (clusterNode.contains(":")) {
                String[] hostAndPortPeers = clusterNode.split(":");
                peerHost = hostAndPortPeers[0];
                peerPort = hostAndPortPeers[1];
            } else {
                peerHost = clusterNode;
                peerPort = "9300";
            }
            try {
                client.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(peerHost),Integer.valueOf(peerPort)));
            } catch (UnknownHostException e) {
                e.printStackTrace();
                return null;
            }

        }
        return client;
    }
}
