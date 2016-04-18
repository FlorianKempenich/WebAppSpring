///<reference path="../../typings/browser.d.ts"/>

class Serializable {
    fillFromJSON(json) {
        for (var propName in json) {
            //noinspection JSUnfilteredForInLoop
            this[propName] = json[propName]
        }
    }


}

class BlogPost extends Serializable{

    title:string;
    post:string;

    getHtml():string {
        return "<h1>" + this.title + "</h1>" +
        "<p>" + this.post + "</p>";
    };

}

//noinspection JSUnusedGlobalSymbols => Used with thymeleaf
function displayPosts(stringPosts:string) {
    var jsonPosts = JSON.parse(stringPosts);
    var posts:Array<BlogPost> = [];
    jsonPosts.forEach(function(jsonPost) {
        var post:BlogPost = new BlogPost();
        post.fillFromJSON(jsonPost);
        posts.push(post);
    });

    posts.forEach(function (post:BlogPost) {
        var htmlPost = "<div class=\"col-md-4\">" +
            post.getHtml() +
            "</div>";

        $("#posts")
            .append(htmlPost);
    });
}