# BotGL - simple render and physics engine

Alpha Version
---------------------

0.1

Introduction
------------

Это библиотека для создания и легкой визуализации среды симуляции поведения ботов написанной на [Scala](http://scala-lang.org/) без лишних зависимостей, поэтому легко партируемая и работающая на [ScalaJS](http://www.lihaoyi.com/hands-on-scala-js/):

задача сделать простой инструмент для создания среды, и визуализации работы работы с ботами для дальнейшего обучения а также с возможностью легко переносить код на соревновательную платформу кодингеймс, практически без изменений (не считая комманды включения / выключения визуализации)


Features
--------
 - Простейший АПИ с набором из 4х обьектов и одной схемой взаимодействия. 
 - Визуализация в браузере (благодаря Scala.JS).
 
Please read the project wiki and especially see [Examples](http://botgl.kotobotov.ru) page to learn more!


Example
-------------------

###Добавление обьектов

   

More examples
-------------

See [Examples Page](http://botgl.kotobotov.ru)

Engine brief description
------------------------

See Engine methods overview

Usage
------------



## Get started

To get started, run `sbt ~fastOptJS` in this example project. This should
download dependencies and prepare the relevant javascript files. If you open
`localhost:12345/target/scala-2.11/classes/index-dev.html` in your browser, it will show you an animated application. You can then
edit the application and see the updates be sent live to the browser
without needing to refresh the page.

## The optimized version

Run `sbt fullOptJS` and open up `index-opt.html` for an optimized (~200kb) version
of the final application, useful for final publication.