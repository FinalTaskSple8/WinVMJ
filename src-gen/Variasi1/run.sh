#!/bin/bash
source ~/.zshrc  

cleanup() {
    pkill -P $$
    rm java.log
    exit 1
}

trap cleanup SIGINT

java -cp siph.product.variasi1 --module-path siph.product.variasi1 -m siph.product.variasi1 2>&1 | tee java.log &
JAVA_PID=$!
TEE_PID=$(pgrep -n tee)
tail -f java.log --pid=$TEE_PID | while read -r LINE; do
    if [[ "$LINE" == *"== CREATING OBJECTS AND BINDING ENDPOINTS =="* ]]; then
        break
    fi
done

echo "SELECT 'CREATE DATABASE siph_product_variasi1' WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = 'siph_product_variasi1') \gexec" | psql "postgresql://postgres:yudisabri123@localhost"
for file in sql/*.sql; do
    psql -a -f "$file" "postgresql://postgres:yudisabri123@localhost/siph_product_variasi1"
done

wait