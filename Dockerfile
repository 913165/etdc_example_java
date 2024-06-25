docker run -it  \
      -p 2379:2379 \
      quay.io/coreos/etcd:latest \
      /usr/local/bin/etcd \
      --listen-client-urls http://0.0.0.0:2379 \
      --advertise-client-urls http://0.0.0.0:2379