/**
 * Created by admin on 31.07.2015.
 */
jQuery(function ($) {
    'use strict';


    $('body').on('click', "#getItem", function () {

        var item_id = $("#item_id").text();
        var postData = {item_id:item_id};

        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "/index/item/getItem",
            dataType:'json',
            data: JSON.stringify(postData),

            success:  function (json) {
                console.log(json);

                var list = '<ul>';
                    list += '<li>';
                    list += '<div>';
                    list += '<span id="item_name/'+json.items[i].id +'" class="fn_text" >' + json.items[i].title + '</span>';
                    list += '<br>';
                    list += '<span id="item_name/'+json.items[i].id +'" class="fn_text" >' + json.items[i].url + '</span>';
                    list += '<br>';
                    list += '<span id="item_name/'+json.items[i].id +'" class="fn_text" >' + json.items[i].description + '</span>';
                    list += '<br>';
                    list += '</div>';
                    list +=  '<hr >';
                    list += '</li>';
                list += '</ul>';

                $('.listitems').append($(list));
            },

            error: function () {
                console.log('error');
            }

        });
    });

    $('body').on('click', "#getItems", function () {

        var fb_id = $("#fb_id").text();
        var postData = {fb_id:fb_id,page:0};
        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "/index/item/getItems",
            dataType:'json',
            data: JSON.stringify(postData),

            success:  function (json) {
                console.log(json);

                var list = '<ul class="search_community">';
                for (var i = 0; i < json.items.length; i++) {
                    list += '<li class="community_search">';
                    list += '<div class="username_community">';
                    list += '<span id="item_name/'+json.items[i].id +'" class="fn_text" >' + json.items[i].title + '</span>';
                    list += '<br>';
                    list += '<span id="item_name/'+json.items[i].id +'" class="fn_text" >' + json.items[i].url + '</span>';
                    list += '<br>';
                    list += '<span id="item_name/'+json.items[i].id +'" class="fn_text" >' + json.items[i].description + '</span>';
                    list += '<br>';
                    list += '</div>';
                    list +=  '<hr >';
                    list += '</li>';
                }
                list += '</ul>';
                $('.listitems').empty();
                $('.listitems').append($(list));
            },

            error: function () {
                console.log('error');
            }

        });
    });




    $('body').on('click', "#showadditem", function () {

        var item = $(".container-fluid");
        if (item.css("display") == "none") {
            $(".container-fluid").css("display","block");
        }
        else {
            $(".container-fluid").css("display","none");
        }

    });




});