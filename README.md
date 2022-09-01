# Photos #

This is a JavaFX project that allows users to manage albums, photos, and tags.

## Login Screen ##

The user is able to log into either the admin account (username: admin, password: admin) or into a standard user account

![login_screen](https://user-images.githubusercontent.com/112635095/187962557-c29bd52c-ab22-48a8-8707-7956ff468057.png)




## Admin Account ##

The admin user is able to manage the standard user accounts. By default, there is a standard stock user account (username: stock, password: stock).

When the admin user logs in, a list of current standard users is displayed.

The admin user has the option to reset the entire application and revert all settings to their default state. There will be a single standard stock user account with stock data.

![admin_screen_user_list](https://user-images.githubusercontent.com/112635095/187962554-af06e5b2-052d-4544-b91f-2ec218fca132.png)

The admin user also has the option to add/remove standard user accounts. Passwords are not necessary for standard user accounts.

![admin_screen_create_user](https://user-images.githubusercontent.com/112635095/187962553-ac67da20-a37c-4a7a-bc5d-0fac5b841f78.png)




## Standard User Account ##

When a standard user logs in, a list of their albums are displayed, along with the date range of photos for each album.

![user_screen_album_list](https://user-images.githubusercontent.com/112635095/187962552-11eb2499-791b-42f8-9509-93bb037b7dbf.png)


### Photo Search ###

The user can search for photos across all of their albums by various criteria. The user can search for photos in a date range or by tags.

![user_screen_photo_search](https://user-images.githubusercontent.com/112635095/187962550-ddbf4d0d-94c1-4392-8fce-3fd20c70d8c3.png)

On the search results page, all photos that match the inputted criteria will be displayed. The user can add these photos to a new album.

![user_screen_photo_search_results](https://user-images.githubusercontent.com/112635095/187962549-9f1e7c8b-ab11-414f-a06d-040817afb6fa.png)


### Album ###

When the user opens an album, a list of all photos in the album is displayed. A small verion of the image along with the name (caption) of the photo is displayed.

![album_screen_photo_list](https://user-images.githubusercontent.com/112635095/187962547-69b3fed3-6e82-4c91-8361-2dc18bfff656.png)

From here, the user can add an image from the local system to the album. The user is also able to delete photos from the album, modify the caption of a photo, copy or move photos to another album.

![add_photo_system](https://user-images.githubusercontent.com/112635095/187966038-610981c2-cfaf-44a8-a1e9-22a37c05b878.png)


### Tags ###

The user can also modify the tags for a photo. By default, there are two tag types: location and person. The user is able to add a tag of these types, or create an entirely new tag type.

![photo_tag_list](https://user-images.githubusercontent.com/112635095/187962546-fea02b53-88b1-45e1-a54b-c15672dd1776.png)


### Photo Slideshow ###

Finally, the user is able to view a larger version of the image. The caption, date, and tag data is displayed beneath the image, and the user is able to flip through all the photos in the album.

![photo_display_screen](https://user-images.githubusercontent.com/112635095/187962543-51c21af6-cae2-47e0-9f76-3530cc43f673.png)




<!-- ![user_screen_create_album](https://user-images.githubusercontent.com/112635095/187962551-2e194788-8acb-45c5-a81c-33de013927f1.png) -->

