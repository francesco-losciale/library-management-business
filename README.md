
# Tiny project for POC, experiments etc




## Latest change

Starting point: simple generic domain classes with unit tests attached all together written in Java 8 (master branch).

Moved the domain classes only from Java to Kotlin, keeping the unit tests in Java 8 (porting-kt branch). 

Code changes necessary for passing the tests:

1. Where intentionally was used null as value in Java, an empty object has been created since in Kotlin null value are not accepted by default (look for "Point 1" in the codebase)
2. Mockito wasn't able anymore to mock some classes since in Kotlin they are final by default (look for "Point 2" in the codebase)
3. Some infix calls are not possible anymore because of the non-nullable variables (look for "Point 3" in the codebase)
4. Using static methods defined as companion objects requires the @JvmStatic annotation (look for "Point 4" in the codebase)


## Change 2 - Simple persistence unit for domain classes

[Basic solution for persisting domain objects](https://github.com/francesco-losciale/library-management-persistence)


## Change 1 - Env set up and initial commit

Added domain classes filled with simple logic to order books and assign them to a courier for delivering.

TDD using JUnit4, Mockito, Maven


## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details
