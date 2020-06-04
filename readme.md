# A little research: Exceptions handler

This project is for exploring Spring Framework binding exception handler.

This shows a problem with different behavior in exception handling.

Two types of method description (`MainRestController`):
1. `requestBody(@RequestBody @Valid DataClass data)`
1. `noRequestBody(@ModelAttribute @Valid DataClass data)`

throws two different exceptions:
1. `MethodArgumentNotValidException` 
1. `BindException`

 respectevly (see `ErrorHandler`).

But! ModelAttributeMethodProcessor.resolveArgument has information about Method.
