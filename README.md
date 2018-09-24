Robert C. Martin Clean Code - A Handbook of Agile Software Craftsmanship Chapter 14 : Successive Refinement

This repo is a practice of step-by-step refinement of an argument parser. The first version is a monolithic but messy file, though it works. The refactoring steps are

1. rollback to the version where the parser only parses boolean inputs.
1. Move the function of parsing inputs to an abstract class ArgumentMarshaler, extended by Boolean, String, Integer, Double
1. Move exception handling to a separate class `ArgsException`
1. Move ArgumentMarshaler to a separate file.

All the refactoring is guided by Test Drive Development. Each modification is paired with a test.
