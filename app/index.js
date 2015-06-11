var yeoman = require('yeoman-generator'),
    _ = require('lodash'),
    util = require('util'),
    path = require('path'),
    cmd  = require('cmd.js');
    
module.exports = yeoman.generators.Base.extend({
    
    reset: function () {
        console.log('Generator reset!');
    },
    
    initializeDir: function() {
        console.log('Inititalizing directories');
    },
    
    promptProjectDetails : function () {
            
        var cb = this.async();
        
        console.log('\n' +
        '~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~' +
        '| SPARK DURANDALJS GENERATOR |' +
        '~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~' +
        '\n');
        
        var prompts = [
            {
                type: 'input',
                name: 'applicationName',
                message: 'What is the name of your application?',
                default: 'myapp'
            },
            {
                type: 'input',
                name: 'packageName',
                message: 'What is your default package name?',
                default: 'com.company.myapp'
            }
        ];

        this.prompt(prompts, function (props) {
            this.applicationName = props.applicationName;
            this.packageName = props.packageName;
            
            cb();
        }.bind(this));
    },
    
    createPOM: function() {
        // TODO: use regex in the replace, make sure we catch all whitespace
        var artifactId = this.applicationName.replace(/s+/, '-').toLowerCase();
        
        var data = {
            'packageName'    : this.packageName,
            'artifactId'     : artifactId,
            'artifactName'   : this.applicationName,
            'templateEngine' : 'velocity'            
        };
        
        this.template('pom.xml', 'pom.xml', data);
    },
    
    projectFiles: function () {
        this.template('bower.json', 'bower.json');
        this.template('Gulpfile.js', 'Gulpfile.js');
        this.template('application.properties', util.format('%s.properties', this.applicationName));
        this.copy('.bowerrc', '.bowerrc');
        this.copy('.editorconfig', '.editorconfig');
        this.copy('.jshintrc', '.jshintrc');
        this.copy('.gitignore', '.gitignore');
        this.copy('findbugs-exclude.xml', 'findbugs-exclude.xml');
        this.copy('README.md', 'README.md');    
        this.copy('.yo-rc', '.yo-rc.json');
    },
    
    sparkapp: function () {
        var self = this;
        var makepath = function(filename) {
            var pathArr = ['src/main/java/' + self.packageName.replace(/\./g, path.sep)];
            pathArr = pathArr.concat(Array.prototype.slice.call(arguments));
            return pathArr.join(path.sep);
        };
        
        this.template('src/main/java/Application.java', makepath('Application.java'));
        this.template('src/main/java/framework/AbstractApplication.java', makepath('framework', 'AbstractApplication.java'));
        this.template('src/main/java/framework/AbstractResource.java', makepath('framework', 'AbstractResource.java'));
        this.template('src/main/java/resources/HelloResource.java', makepath('resources', 'HelloResource.java'));
        this.template('src/main/java/controllers/HelloController.java', makepath('controllers', 'HelloController.java'));
        this.template('src/main/java/util/JsonTransformer.java', makepath('util', 'JsonTransformer.java'));
        this.template('src/main/java/util/CorsFilter.java', makepath('util', 'CorsFilter.java'));
    },
    
    durandalapp: function () {
        var makepath = function (filename) {
            var pathArr = ['src/main/resources/assets/'];
            pathArr = pathArr.concat(Array.prototype.slice.call(arguments));
            return pathArr.join(path.sep);
        };
        
        this.template('src/main/resources/assets/index.html', makepath('index.html'));
        this.template('src/main/resources/assets/app/index.js', makepath('app', 'index.js'));
    }
});