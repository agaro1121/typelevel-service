#!/usr/bin/env bash

docker run -p5432:5432 --name monkey-postgres -e POSTGRES_PASSWORD=monkeypassword -e POSTGRES_USER=monkeyman -d postgres