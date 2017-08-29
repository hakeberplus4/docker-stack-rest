package org.millersrock.docker.stack;

import com.spotify.docker.client.exceptions.DockerCertificateException;
import com.spotify.docker.client.exceptions.DockerException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackageClasses = StackRestApi.class)
public class MainApplication {

    public static void main(String[] args) throws InterruptedException, DockerException, DockerCertificateException {
        DockerMain.main(args);
        SpringApplication.run(MainApplication.class, args);
    }


}
