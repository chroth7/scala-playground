# Scala-Playground

## Disclaimer

This is a repo that was created during the study of the underscore book, see:

[Underscore.io - Essential Scala](https://underscore.io/training/courses/essential-scala/)

While reading the book, I tried to use proper build tools, including tests - using `sbt`.

I was/am still new to `sbt`, so it could be that it is not all best-practices.

Any tips, PRs, ideas, etc. are very welcome!

## Development

The code is built according to `sbt` guidelines, see:

[sbt Reference Manual](https://www.scala-sbt.org/1.x/docs/)

Run `sbt` in a different console.

Console: just run `console` in `sbt`.

Note: it should be possible to load files in console, like `:load src/main/scala/example/underscore4/Underscore4.scala`, but there is an issue right now. To be checked.

### Source

See `./src/main/scala/example/...`

Use `~run` to run the program in `sbt`. (the tilde re-runs on changes, automatically).

### Tests

See `./src/test/scala/...`

Use `~test` or `~testQuick` (the latter should only pick up incremental tests) to continuously run the test suite(s).
