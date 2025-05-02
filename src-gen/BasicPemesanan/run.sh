#!/bin/bash
source ~/.zshrc  

cleanup() {
    pkill -P $$
    rm java.log
    exit 1
}

trap cleanup SIGINT

java -cp siph.product.basicpemesanan --module-path siph.product.basicpemesanan -m siph.product.basicpemesanan 2>&1 | tee java.log &
JAVA_PID=$!
TEE_PID=$(pgrep -n tee)
tail -f java.log --pid=$TEE_PID | while read -r LINE; do
    if [[ "$LINE" == *"== CREATING OBJECTS AND BINDING ENDPOINTS =="* ]]; then
        break
    fi
done

echo "SELECT 'CREATE DATABASE siph_product_basicpemesanan' WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = 'siph_product_basicpemesanan') \gexec" | psql "postgresql://:@localhost"
for file in sql/*.sql; do
    psql -a -f "$file" "postgresql://:@localhost/siph_product_basicpemesanan"
done

wait