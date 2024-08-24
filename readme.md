# Spatial Server

## Introduction

A JVM based tile and feature server based on PostgreSQL and
PostGIS. This tool is inspired by
[pg_featureserv](https://github.com/CrunchyData/pg_featureserv) and
[pg_tileserve](https://github.com/CrunchyData/pg_tileserv) by CrunchData.

This tool is designed to be easy to deploy and run in your cloud
infrastructure. All you need is a system that can run Java 11 and
PostgreSQL. Spatial Server takes care of the rest.

Spatial Server implements several of the latest OCG standards,
including the **OGC Feature Service**, the **OGC Tile Serve API** and the **OGC
Processing API**.

The combination of these three API should give users the ability to
edit, render and analysis spatial information.

Spatial Server also exposes its internal metrics via Open Telemetry
(OTEL). This will allow you to monitor your server via open source and
standard tools.

## Develop

## Install
