#!/usr/bin/env php
<?php

set_time_limit(0);

/** @var string $version */
$version = $_ENV['WEBSERVER_VERSION'] ?: 'undefined';
/** @var string $env */
$env = $_ENV['WEBSERVER_ENVIRONMENT'] ?: 'prod';
/** @var boolean $debug */
$debug = ($_ENV['WEBSERVER_DEBUG'] ?: 'off') === 'on' && $env !== 'prod';

echo 'Hello world!'.PHP_EOL.'cli front controller / '.$version.PHP_EOL;
sleep(15); // simulates heavy process
