FROM php:7.4.3-fpm

RUN docker-php-ext-install -j$(nproc) mysqli \ 
    pdo \ 
    pdo_mysql