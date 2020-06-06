# A little research: Exceptions handler

This project is for exploring Spring Framework binding exception handler.

This project shows a problem with various behavior in exception handling.

Two types of method description (`MainRestController`):
1. `requestBody(@RequestBody @Valid DataClass2 data)` // no file or file handling
1. `noRequestBody(@ModelAttribute @Valid DataClass data)` // file in data
1. `noRequestBody2(@ModelAttribute @Valid DataClass2 data, @RequestPart(value = "file", required = true) final MultipartFile f)` // file as the multipart parameter

throws three different exceptions:
1. `MethodArgumentNotValidException` 
1. `BindException`
1. `MissingServletRequestPartException`

 respectevly (see `ErrorHandler`).

`ModelAttributeMethodProcessor.resolveArgument` (2) and `RequestPartMethodArgumentResolver.resolveArgument` (3) 
have information about `Method` and other contextual informatoion, the same as in `RequestResponseBodyMethodProcessor.resolveArgument` (1),
but in exception we does not have identical information.

In my opinion, this is same problem in request and the exceptions should have identical contextual information inside.

# Run project

`mvn clean test`

# Switch between Spring Boot versions

Go into `pom.xml` and select `spring-boot-starter-parent` in `parent` section.
