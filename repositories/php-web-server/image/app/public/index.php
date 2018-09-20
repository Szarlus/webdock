<?php

require_once __DIR__.'/../vendor/autoload.php';

/** @var string $version */
$version = $_ENV['WEBSERVER_VERSION'] ?: 'undefined';
/** @var string $env */
$env = $_ENV['WEBSERVER_ENVIRONMENT'] ?: 'prod';
/** @var boolean $debug */
$debug = ($_ENV['WEBSERVER_DEBUG'] ?: 'off') === 'on' && $env !== 'prod';

/*
 * Some log
 */
$logger = new \Monolog\Logger('main');
$handler = new \Monolog\Handler\StreamHandler('php://stderr');
$formatter = new \Monolog\Formatter\JsonFormatter();

$handler->setFormatter($formatter);
$logger->pushHandler($handler);

$logger->info('Web front controller logging stuff.', ['env' => $env]);

/*
 * Some output
 */
echo 'Hello world!<br>web front controller / '.$version;
