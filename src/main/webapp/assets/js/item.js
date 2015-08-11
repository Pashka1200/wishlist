/**
 * Created by admin on 31.07.2015.
 */
jQuery(function ($) {
    'use strict';

    $(document).ready(function() {
        var url = window.location.href;
        var pos = url.substring(url.lastIndexOf('/') + 1);
        var item = pos.substring(0,5);
        var item_id = pos.substring(4);

        if (item == "fitem") {
            $.getJSON('/index/fitem' + item_id + '/getItem', {}, function (json) {
                console.log(json);
                $('#item_name').text(json.item.title);
                $('.main').css("width", "80%").append("<div style='width:100%; text-align: center; '><img src='/assets/images/bought@3x.png' style='width:50%; margin-toP: 15%;' id='buy_item' /></div> ");
                $('#update').remove();
//               $('.item_picture').attr("src",json.item.picture);
                $('.item_picture').attr("src", "/assets/images/rectangle19@3x.png");
                $('.item_description_text').text(json.item.description);
                $('.item_url_text').text(json.item.url);
                $('#item_url_text').attr("href", json.item.url);
                $('.container').remove();
            });
        }
        else {

            $.getJSON('/index/item' + item_id + '/getItem', {}, function (json) {
                console.log(json);
                $('#item_name').text(json.item.title);
                $('.main').css("width", "80%");
//               $('.item_picture').attr("src",json.item.picture);
                $('.item_picture').attr("src", "/assets/images/rectangle19@3x.png");
                $('.item_description_text').text(json.item.description);
                $('.item_url_text').text(json.item.url);
                $('#item_url_text').attr("href", json.item.url);
                $('.container').remove();
            });
        }
    });

    $('body').on('click', "#buy_item", function () {

        var fb_id = $.cookie("fb_id");
        var url = window.location.href;
        var pos = url.substring(url.lastIndexOf('/') + 1);
        var item_id = pos.substring(5);
        var postData = {buyer_id:fb_id,item_id: item_id};
        console.log(postData);

        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "/index/reserve/addBuyer",
            dataType:'json',
            data: JSON.stringify(postData),

            success:  function (json) {
                console.log(json);
                $("#buy_item").fadeOut(500);
            },

            error: function () {
                console.log('error');
            }

        });
    });


    $('body').on('click', "#update", function () {

        var title = $("#item_name").text();
        var picture = $(".item_picture").attr("src");
        var url = $(".item_url_text").text();
        var description = $(".item_description_text").text();

        $("#item_name").replaceWith('<input type="text" value="' + title +'" >');
        $("header").css("min-height","150px");
        $(".main").empty().append("<div class='description_block'><label>Description:</label>" +
            "<textarea id='description' placeholder='Describe your wish here!'>" + description + "</textarea><br>" +
            "<label>URL:</label><input type='text' id='url' value='"+ url +"' placeholder='Add url for your Wish' style='margin-top:20px;'></div>" +
            "<button id='add_item'>Update Your Wish</button>");

    });




    $('body').on('click', "#update_item", function () {

        var url1 = window.location.href;
        var pos = url1.substring(url1.lastIndexOf('/') + 1);
        var item_id = pos.substring(4);
        var title = $("#item_name").text();
        var picture = $(".item_picture").attr("src");
        var url = $(".item_url_text").text();
        var description = $(".item_description_text").text();
        var postData = {item_id: item_id,title:title,url:url,description:description,picture:picture};

        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "/index/items/updateItem",
            dataType:'json',
            data: JSON.stringify(postData),

            success:  function (json) {
                if (json == ("true"))
                location.reload();
                else {
                    console.log("error");
                }
            },

            error: function () {
                console.log('error');
            }

        });
    });


});