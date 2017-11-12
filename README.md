# LearnPlayProject
Проект для изучения Scala, Play Framework 2, ScalaTest, Google Guice и ряда других технологий,
список которых будет пополняться по мере их появления в проекте.

Постановка задачи:
Необходимо реализовать веб приложение, способное находить пересечение заданного пути с окрестностями заданных объектов.

Допушения:
1) Окрестность объекта может быть только выпуклым многоугольником. Это позволяет достаточно просто проводить триангуляцию.

Текущие функции:
Rest api:
1) post /addFigure  добавляет окрестность. Проверяет,что это правильный многоугольник, если нет возвращает 400
2) get /getFigures возвращает все окрестности
3) post /checkRoute  проверяетчерез какие окрестности проходит заданный путь

По /  показывается страница с отрисованными окрестностями и последним путем
