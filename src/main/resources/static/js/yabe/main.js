///<reference path="../../typings/browser.d.ts"/>
var __extends = (this && this.__extends) || function (d, b) {
    for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p];
    function __() { this.constructor = d; }
    d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
};
var Serializable = (function () {
    function Serializable() {
    }
    Serializable.prototype.fillFromJSON = function (json) {
        for (var propName in json) {
            //noinspection JSUnfilteredForInLoop
            this[propName] = json[propName];
        }
    };
    return Serializable;
}());
var BlogPost = (function (_super) {
    __extends(BlogPost, _super);
    function BlogPost() {
        _super.apply(this, arguments);
    }
    BlogPost.prototype.getHtml = function () {
        return "<h1>" + this.title + "</h1>" +
            "<p>" + this.post + "</p>";
    };
    ;
    return BlogPost;
}(Serializable));
//noinspection JSUnusedGlobalSymbols => Used with thymeleaf
function displayPosts(stringPosts) {
    var jsonPosts = JSON.parse(stringPosts);
    var posts = [];
    jsonPosts.forEach(function (jsonPost) {
        var post = new BlogPost();
        post.fillFromJSON(jsonPost);
        posts.push(post);
    });
    posts.forEach(function (post) {
        var htmlPost = "<div class=\"col-md-4\">" +
            post.getHtml() +
            "</div>";
        $("#posts")
            .append(htmlPost);
    });
}
//# sourceMappingURL=main.js.map