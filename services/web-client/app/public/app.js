var env = typeof window._app_environment === "undefined" ? "prod" : window._app_environment;
var debug = (typeof window._app_debug === "undefined" ? "off" : window._app_debug) === "on" && env !== "prod";

document.write("Hello world!<br>spa / " + window._app_version);
