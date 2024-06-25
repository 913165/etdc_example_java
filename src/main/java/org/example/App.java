package org.example;

import io.etcd.jetcd.Client;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.CompletableFuture;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println("main started");
        // create etcd client using ip address 35.238.163.251 and port 2379
        Client client = Client.builder().endpoints("http://35.238.163.251:2379").build();
        System.out.println("Connected to etcd server");

        // put the key mykey and value Hello, etcd
        try {
            client.getKVClient().put(
                    io.etcd.jetcd.ByteSequence.from("mykey2", StandardCharsets.UTF_8),
                    io.etcd.jetcd.ByteSequence.from("Hello, etcd2", StandardCharsets.UTF_8)
            ).get();
            System.out.println("Key and value inserted");
            // get the value of the key

            CompletableFuture<io.etcd.jetcd.kv.GetResponse> getFuture = client.getKVClient().get(
                    io.etcd.jetcd.ByteSequence.from("mykey2", StandardCharsets.UTF_8));

            io.etcd.jetcd.kv.GetResponse response = getFuture.get();

            for (io.etcd.jetcd.KeyValue kv : response.getKvs()) {
                System.out.println("Key: " + kv.getKey().toString(StandardCharsets.UTF_8));
                System.out.println("Value: " + kv.getValue().toString(StandardCharsets.UTF_8));
            }
            System.out.println("Key and value retrieved");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("main ended");

    }
}
