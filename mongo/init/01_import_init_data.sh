#!/bin/zsh
mongoimport --db SAMPLE_DB --collection SEQUENCE --drop \
            --file /docker-entrypoint-initdb.d/01_import_init_data_sequence.json \
            --jsonArray

mongoimport --db SAMPLE_DB --collection USER --drop \
            --file /docker-entrypoint-initdb.d/01_import_init_data_user.json \
            --jsonArray
