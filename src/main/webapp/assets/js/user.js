/**
 * Created by admin on 31.07.2015.
 */
jQuery(function ($) {
    'use strict';

//      *******MAIN FUNCTIONS*******
    $( document ).ready(function() {

            var fb_id = $.cookie("fb_id");
            var page  = 0;
            var postData = {fb_id:fb_id,page:page};
            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: "/index/items/getItems",
                dataType:'json',
                data: JSON.stringify(postData),

                success:  function (json) {
                    var list = '<ul class="item_list">';
                    if (json.items.length > 0 ) {
                        for (var i = 0; i < json.items.length; i++) {
                            list += '<li class="item_li">';
                            list += '<div class="item">';
                            //  list += '<a href="/index/item' + json.items[i].id + '" >';
                            list += '<div class="img_container">';
                            list += '<img src="/assets/images/rectangle19@3x.png" class="item_picture" >';
                            list += '</div>';
                            //  list += '</a>';
                            list += '<div class="text_block">';
                            list += '<a href="/index/item' + json.items[i].id + '" >'; //style="float:left,width:70%"
                            list += '<span id="item_name/' + json.items[i].id + '" class="item_title" >' + json.items[i].title + '</span>';
                            list += '</a>';
                            list += '<span id="item_name/' + json.items[i].id + '" class="item_description" >' + json.items[i].description + '</span>';
                            list += '</div>';
                            list += '</div>';
                            list += '<div class="center_line"></div>';
                            list += '</li>';
                        }
                    }
                    list += '</ul>';
                    $('.main').empty().append($(list));
                    $('.container').remove();

                },

                error: function () {
                    console.log('error');
                }

            });
    });

    $('body').on('click', ".add_item", function () {
        $(".add_item").remove();
        $("header").css("min-height","150px");
        $(".right_header").empty().append("<div class='block_add_title'><label>Wish Title:</label><input type='text' id='title' placeholder='title'></div>");
        $(".left_header").empty().append("<img src='/assets/images/gift.png' class='avatar'>");
        $(".main").empty().append("<div class='description_block'><label>Description:</label>" +
            "<textarea id='description' placeholder='Describe your wish here!'></textarea><br>" +
            "<label>URL:</label><input type='text' id='url' placeholder='Add url for your Wish' style='margin-top:20px;'></div>" +
            "<button id='add_item'>Add Your Wish</button>");
    });



    $('body').on('click', "#update_item", function () {
        var title = $("#title").val();
        var picture = $("#picture").val();
        var url = $("#url").val();
        var description = $("#description").val();
        var item_id = $("#item_id").val();

        var postData = {item_id: item_id,title:title,url:url,description:description,picture:picture};
        console.log(postData);

        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "/index/item/updateItem",
            dataType:'json',
            data: JSON.stringify(postData),

            success:  function (json) {
                console.log(json);
            },

            error: function () {
                console.log('error');
            }

        });
    });

    $('body').on('click', "#del_item", function () {

        var item_id = $("#item_id").val();
        var postData = {item_id: item_id};
        console.log(postData);

        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "/index/item/delItem",
            dataType:'json',
            data: JSON.stringify(postData),

            success:  function (json) {
                console.log(json);
            },

            error: function () {
                console.log('error');
            }

        });
    });

    $('body').on('click', "#add_item", function () {

        $('#add_item').prop('disabled',true);
        var title = $("#title").val();
        var picture = $("#picture").val();
        var url = $("#url").val();
        var description = $("#description").val();
        var fb_id = $.cookie("fb_id");

        var postData = {fb_id: fb_id,title:title,url:url,description:description,picture:picture};
        console.log(postData);

        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "/index/items/addItem",
            dataType:'json',
            data: JSON.stringify(postData),

            success:  function (json) {
                console.log(json);
                if (json.status != "false" || json != "undefined") {
                    $('#add_item').remove();
                    $('#add_item').prop('disable',false);
                    window.location.href = "/index/item"+json.itemId;
                }
            },

            error: function () {
                console.log('error');
            }

        });
    });


});