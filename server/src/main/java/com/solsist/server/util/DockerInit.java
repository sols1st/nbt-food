package com.solsist.server.util;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.model.Info;
import com.github.dockerjava.core.DockerClientBuilder;

import java.util.List;

public class DockerInit {
    public DockerClient connectDocker(){
        DockerClient dockerClient = DockerClientBuilder.getInstance("tcp://cloud.nbtca.space:2375").build();
        Info info = dockerClient.infoCmd().exec();
        System.out.println("docker的环境信息如下：=================");
        System.out.println(info);
        System.out.println(info.getContainers());
        System.out.println(info.getOperatingSystem());
        System.out.println(info.getArchitecture());
        System.out.println(info.getName());
        List imageInfo = dockerClient.listImagesCmd().exec();
        for(Object image : imageInfo){
            System.out.println(image);
        }
        return dockerClient;
    }

    public static void main(String[] args) {
        DockerInit dockerInit = new DockerInit();
        dockerInit.connectDocker();
    }
}
