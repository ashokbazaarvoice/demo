require.config({
    paths: {
        'jQuery': 'vendor/jquery-1.9.0.min',
        'underscore': 'vendor/underscore-1.9.min'
    },
    shim: {
        'jQuery': {
            exports: '$'
        },
        'underscore': {
            exports: '_'
        }
    }
});

require(['user', 'blog/post'], function(u, p){
    var user = new u(), post = new p();
    post.makePost();
});

