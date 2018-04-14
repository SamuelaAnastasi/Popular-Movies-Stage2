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

![Movie Main Screen](https://lh3.googleusercontent.com/qgwcBxRawrb0AMOALd07V2T7IVhZ4SZwRR4LSH7fZeZneKEPavAJAsRT6wYmleDZCZcrV3wfbnHs12_i8lGa1KQbMLD2q0e0rCIIBl4qMqhMFf7KG6bLuwG5BUdMg1U93aIMp44WBzGFS0nlPt8tKPEEFe6z2ltW0X40amHgqD-XBhyu-KjZtGtYF-EVtTcRToLPVw0JvJBdVgBFYLmyKudmFeEsfqQif7-UTmhHQERFbl9aFsrt9VZxCZUDqvMjt7YM-4tGGl3dneJdON6aNWk63sbewBGPG69HT2DgfRdwesE9NkNjTnrzvsfinHghTBCMabPePFgRZJ8DtIS4w1Erp2H6pkunSyaoYgfrGn12fEW3XYcm9wkxIzGKcJip18A9Du_PH_osy6i-tcsv1PHg45-h-sXn8YBGHrOn7nNKRiW8I00yXM42i2zoWzyOgzGKS7RmH0Z2R84e0tMWI4pFEkyIcg_7ot014URxDiJzFl8rb95HCqX4BRD5-mnqcTpFW8PFHUnjA8jvFRpq0_QQVe9NAnvEQjElFemm1ubwOG-iJQVZWOZ3e03AB1KshTpSBtwFcq4V4BHoY3G290WlsTploYWRzznnjIkOtIJ7xDE8cpNJs8j2UM5UVwDUaZV4v1pAK_mTukOE8FqEII9kvGNAxq8=w531-h943-w400-no)  ![Movie Info Overview](https://lh3.googleusercontent.com/5Q86tt41rlv-fla3IdXH5sC2hGSJw3WFrEDDWvtREzX2CIH73yjgBCeQhcucLCPfKMxWCHUoCWbCFKiLW1VdiwPUlZg6pkh6OfHiIOexXbvT9yITJZPAmfWIkT8sH58zoZZqnnDryJOqXP3UYTYmJkdhXUq2pNgfBtWtia8hVK5aWqrcmLzB2leOYtt3GSWu2nwE6JENUx4PqDTsuP6bTUFy86oFMfn9zmPVKNRNnH427XQF2UuzJGbSGP4AOqNBPzyz-ChskaSw_EGdErPUjQljwMy7f4cuoexiZprMIPKGZUpwRrI3v-h86_yoXd4CVRZWBhE22JmvqCUUnyknjygm4py437CWF0leFebZxlVI-eg4_Sb5V2Nk86xWcLvzkJLBwunM5ouKDTQzB9LMSBC0ZK9vTi5aspVUxF_vv78Qc1XQEq5XqLSfV-mdAEUhp7mlOBAFDhw5OYoKQiqqgJG4LMKqWzj17ktTijCUpNbbld6nW63NCwlFzp9YZghdtPFpdFFvQJt_ZKMyBLwlBsiMPFC016PbxQ8OaFQf2OnheCw2Qt1G1cBDJqdQ=s949-w534-h949-w400-no)
![Movie Info Trailers](https://lh3.googleusercontent.com/bBxIdKefK3MCZLHo09c3mAsTdD5DcjWwFGTwLz0oXCx8W1r3m4L1WB2uUZKPfEy5R1W7n1pcGFqjRrCHf7TiZToQLDTkfSgWzmzwkRCY429jxH-QQVPzfdrW_LboGNcveIbfzhgzhdc0Ve-che4nB3-7Ad-W6Vri48wmjWFLFWWZllGRfZ2zsAbJhohsaHfyxmklJyk4e_nRss3Cj-Ga3wl8W5Lr64Ct2ooqReZ-UhQCbv5JedUWxY80dvkT6tcLpRUOvnJ-fI1RLC9hlpq9dCbm7LmeN3WOQH_K1x7BY_GfzrnX3tcsAzNktrC0UYmXhy0Rm9NArjGnpbijnh5PV_jFbhVbRjO2XJjv27YYK_GK7rw185m4ig7_S5KnBEoZqwDAJ9WzovpaUyu6gE8h81APtqFnO1WvPPq12QMKj2aifZzzhcJaewJmUGxotBv0k0MqqNZyFFtuGgSOmYYBU3iyg1izKeCfx3Qvn0GEtnPobyKzPUAHG-fc-zBVz66bf4cbfjrE2fRu0bR1FMCH6tu61Rp-Sy-kg6QG89cW5SL_AT7_j0r5AvXYkOD-=s949-w534-h949-w400-no) ![Movie Info Reviews](https://lh3.googleusercontent.com/XIZBoYl1LR3ENgGFZKqoCHvdgKD1uAxQXtvnvwvDI97tEONnxN5KJkO7yapygS3S2Aq0agEBbk1W4ymw3nw-Hw7gVdExhUO80iuYL82xJrZcrCirDaeUeWRWyJOs6Rmo4nA_j_2KqXYseH4j83tx-pu48HEBefvyCxGHkai_dFRQqC2AkmO0r09DPUBZttMGAH6erLt395CyPRVnNGtHLYHQLDf_xbkqVMsCOX5d_juSrCR5n7i1Yq36F9NxAm3GyRogMOy4aR336libeiVFFpJqxraPHeMHRYLE9u4YkWQYmTvsh4fFrdeS4uN7fUvnZS37H1bRaY6o0h0r5OQvlLeGPgEjhjxBoHPuymR2zCVGTBfmFCM43OmyxKCJhu6hpO5OFfSXAphaBvOfTmWvi3Ex0XmuadF0t93ICoIlPesIJQhF9BeUMkhIgYJMZndHFcXVp-kA2Og-CdCvoGiRRoHAB9H7e4PWhC8H9vlEbDi6-7pM6xULagQSd2niSPwMF99soR934kiPG4UCZWx1HPDoxXuwHQLIqsQgtXuiigWp4dHWNEISI3PfRNXkUMVBGYR0iuCvT_eY4hoWzc-4RTvqvBVeNeYEwfCBpYHqT3irs5JqmobjfDYCqssnhBMfPVDH7mpsffQY8mQgcO1wTMwNR7bXom4=w424-h754-w400-no)


## Instructions
After creating a free account you need to request a API KEY from [TMDb](https://www.themoviedb.org/documentation/api). For more instructions see the [API FAQs](https://www.themoviedb.org/faq/api). Then in **gradle.properties** file in your project place the API key:
```
    API_KEY="your_api_key"
```
## Libraries
The project uses the following libraries:
* [Butter Knife](http://jakewharton.github.io/butterknife/)
* [Picasso](http://square.github.io/picasso/)

### Project Licence
This project is licenced under the [MIT Licence](https://opensource.org/licenses/MIT)

Copyright (c) 2018 - Samuela Anastasi
