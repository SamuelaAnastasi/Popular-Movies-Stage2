# Popular Movies - Stage 2

This project is part of [**Android Developer Nanodegree Scholarship Program**](https://www.udacity.com/google-scholarships) by
**Udacity** and **Google**. The project's purpose is to build an app that retrieves
movie data from The Movie Database API [TMDb](https://www.themoviedb.org/documentation/api).
## Features
The project presents the users a grid arrangement of movie elements each including the movie poster, title and genres. The user may choose through a _BottomNavigationView_ to retrieve either Popular or Top Rated movies.
Selecting a movie will open a _Movie Info_ screen with more detailed
information like: movie title, overview, release date, genres and user ratings.
In this final part of the project the _Movie Info_ activity includes also:
* A list of YouTube trailers with image preview that can be watched on-line.
* A list of member reviews: Each list item is expandable on-click.
* A FAB button to toggle `insert/delete` from a local SQLite Database.

A list of all database movies and relative poster, title and genres can be retrieved through _BottomNavigationView_ Favorites button

## Screenshots

![Movie Main Screen](https://lh3.googleusercontent.com/qgwcBxRawrb0AMOALd07V2T7IVhZ4SZwRR4LSH7fZeZneKEPavAJAsRT6wYmleDZCZcrV3wfbnHs12_i8lGa1KQbMLD2q0e0rCIIBl4qMqhMFf7KG6bLuwG5BUdMg1U93aIMp44WBzGFS0nlPt8tKPEEFe6z2ltW0X40amHgqD-XBhyu-KjZtGtYF-EVtTcRToLPVw0JvJBdVgBFYLmyKudmFeEsfqQif7-UTmhHQERFbl9aFsrt9VZxCZUDqvMjt7YM-4tGGl3dneJdON6aNWk63sbewBGPG69HT2DgfRdwesE9NkNjTnrzvsfinHghTBCMabPePFgRZJ8DtIS4w1Erp2H6pkunSyaoYgfrGn12fEW3XYcm9wkxIzGKcJip18A9Du_PH_osy6i-tcsv1PHg45-h-sXn8YBGHrOn7nNKRiW8I00yXM42i2zoWzyOgzGKS7RmH0Z2R84e0tMWI4pFEkyIcg_7ot014URxDiJzFl8rb95HCqX4BRD5-mnqcTpFW8PFHUnjA8jvFRpq0_QQVe9NAnvEQjElFemm1ubwOG-iJQVZWOZ3e03AB1KshTpSBtwFcq4V4BHoY3G290WlsTploYWRzznnjIkOtIJ7xDE8cpNJs8j2UM5UVwDUaZV4v1pAK_mTukOE8FqEII9kvGNAxq8=w531-h943-w400-no)  ![Movie Info Overview](https://lh3.googleusercontent.com/3ZXJE0XtBwU706rbdUiiufjCVcd9wDxoISb_PXTkPbJ1cEHL95TvSi0VDSqKG_64qGg02lpB4bw6zGFNH42PK2IilOh5qO2fCO9aJv-S6YEvygxmtDusaVY0fwLNac_uM7WXc5lP9oFTQvbgvp2kCKoA56K8ZhmQWM4a1c_h7NfZSFT0eYnYTpspLy6oiCYfyIndFgM-UP4uy-NungXfux3fz8j4SOzRrekrNkoZ_gp-3d2tWwRrrb_mGzjyivSkn-Fbpx_DXTZSXTAvhrBMkjpcCDgWc3BSbx8xFxYgTWtnkz8tirJZyQKRXusg-sqCkzAUMotq-ofjAYTD0VJe99ccey46WtUqgGXtMKLa5-2Q6viq_uoi2Bo8CQe3z7ubjOvqQVDpiE0c1tPRzaImnT7pnBwx-Uq_w9Kvx7njde7Ohb-2FAx_FpLtAo6170PZJ5YaRmKqbJmYjQ85ftLgr59oD-MdAZFZQRTP-TjFhn26kOAkpTEFG4ihjEHikRL3HBkCH0s0kfdJgxXtvchRbv-bwzwkRnua7_TGdt85bgRgfA3wXuZRl0_GRM9hEzaGvvP4vK8YLyz-AaeFNgvOAdssTgWwqlVZnfgACAadAbN1c-CIFinABeHE5u4KDbsjVVVEuEI49BvACX-Idemwk6MoCtHwTzc=w534-h949-w400-no)
![Movie Info Trailers](https://lh3.googleusercontent.com/det47mWlwRo3fjdeezNQHsbgMWhF8Ltm4SsXhefdBQ3lO5vWH-c4G2kbeiP60zqqf6HxpsMtzu5pd6iok1U7wJGyjk7uzl-AkaL6HVEMitVZ78xgjt9oFT40TBvQfRKIv_-RlSTTRksoU-2ct5NuGydSCAWxKJp-JZNStGC1JgEzgkVcreGLgfmu_sU_o0cJ4iHuWa7dIE1UQf_RX3slTUANUJJce2fPabbER67Bl2auqzTlfM3ylqdrkToBIUIQTlNlFgx9PHux9yzHhA7WAkZfBHfggfCbCThCVihS2W4xoEkkLFseQdogopbH_QIQifxkupaa_TQKCt7-o_u0TrKsTFAFtX7gXohU3H-x8WY1E6qiTb4sGUD1fu-KSwht6tEBGp3MbnCo3eYPNKf4DPkcQ2KlRl4Cs3tpRjyO6k5iehDeFSRgujtv_WqbL7ur-nHPskK4KoXlB7Ecrk_12L9P9yvlJP2TpNSzYjZH7b8WHsyJYhfeJ7DnXQH71ly0gGWXppCmVPSoMNtYm5_ONX1LVtePwcuJI1h0fxdNjCFhUwEyNiFztv4sCHzeVQ7qBwsTSz6eQZExfAIhDnpjnKrjJYuJWezj9TNfDI1526ksoEeTclCu4XcWS7tRmpQklADzUHKoDpwEX5DxYmKlA9XPfDZScN4=w534-h949-w400-no) ![Movie Info Reviews](https://lh3.googleusercontent.com/XIZBoYl1LR3ENgGFZKqoCHvdgKD1uAxQXtvnvwvDI97tEONnxN5KJkO7yapygS3S2Aq0agEBbk1W4ymw3nw-Hw7gVdExhUO80iuYL82xJrZcrCirDaeUeWRWyJOs6Rmo4nA_j_2KqXYseH4j83tx-pu48HEBefvyCxGHkai_dFRQqC2AkmO0r09DPUBZttMGAH6erLt395CyPRVnNGtHLYHQLDf_xbkqVMsCOX5d_juSrCR5n7i1Yq36F9NxAm3GyRogMOy4aR336libeiVFFpJqxraPHeMHRYLE9u4YkWQYmTvsh4fFrdeS4uN7fUvnZS37H1bRaY6o0h0r5OQvlLeGPgEjhjxBoHPuymR2zCVGTBfmFCM43OmyxKCJhu6hpO5OFfSXAphaBvOfTmWvi3Ex0XmuadF0t93ICoIlPesIJQhF9BeUMkhIgYJMZndHFcXVp-kA2Og-CdCvoGiRRoHAB9H7e4PWhC8H9vlEbDi6-7pM6xULagQSd2niSPwMF99soR934kiPG4UCZWx1HPDoxXuwHQLIqsQgtXuiigWp4dHWNEISI3PfRNXkUMVBGYR0iuCvT_eY4hoWzc-4RTvqvBVeNeYEwfCBpYHqT3irs5JqmobjfDYCqssnhBMfPVDH7mpsffQY8mQgcO1wTMwNR7bXom4=w424-h754-w400-no)


## Instructions
After creating a free account you need to request a API KEY from [TMDb](https://www.themoviedb.org/documentation/api). For more instructions see the [API FAQs](https://www.themoviedb.org/faq/api). Then in **gradle.properties** file in your project place the API key:
```
    API_KEY="your_api_key"
```
## Libraries
The project uses the following libraries:
* [Butter Knife](http://jakewharton.github.io/butterknife/)
* [Picasso](http://square.github.io/picasso/)

### Project License
This project is licensed under the [MIT Licence](https://opensource.org/licenses/MIT)

Copyright &copy; 2018 - Samuela Anastasi
