define(function (require) {
    'use strict';
    
    var http = require('plugins/http'),
        app = require('durandal/app'),
        ko = require('knockout'),
        HelloViewModel = function () {
            this.name = ko.observable('Stranger');
            
            this.sayHello = function () {
                http.get('/api/hello')
                    .then(function (data) {
                        app.showMessage(data.message);
                    })
                    .catch(function () {
                        app.showMessage('There was a problem connecting with the API');
                    });
            };
        };
    return HelloViewModel;
});