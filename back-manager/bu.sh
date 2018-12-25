npm run build
tar -zcvf back-front.tar.gz dist/

scp back-front.tar.gz root@47.100.163.14:/root/deploy/redhat/backend-api-front
