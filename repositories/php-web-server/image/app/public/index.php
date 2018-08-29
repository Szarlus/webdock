<?php

/** @var string $version */
$version = $_ENV['WEBSERVER_VERSION'] ?: 'undefined';
/** @var string $env */
$env = $_ENV['WEBSERVER_ENVIRONMENT'] ?: 'prod';
/** @var boolean $debug */
$debug = ($_ENV['WEBSERVER_DEBUG'] ?: 'off') === 'on' && $env !== 'prod';

echo 'Hello world!<br>web front controller / '.$version;
