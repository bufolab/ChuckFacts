# ChuckFacts
ChuckFacts is a demo Android App to show different arch patterns among other experiments. 
The best is that it tells you ChuckNorris facts!

The repository is divided in branches. Each branch represent a sample of an architecture that has been commonly used in Android.

All of them use RxJava. So we can get rid of Buses that makes difficult to follow the code.

The App retrieves ChuckNorris facts and shows them to the user in a TinderLike Deck.

Thanks to [ChuckNorris](https://github.com/chucknorris-io) for the Api

## MVP-RX
The classic Model View Presenter following 
[clean architecture(]https://8thlight.com/blog/uncle-bob/2012/08/13/the-clean-architecture.html)

No IoC or DI applied in this branch (for the moment) so the coupling still exists.

This repository is intented to experiment with LayoutManager. So you see how a "Deck" or "Stack" of cards can be created
just using a custom LayoutManager.

You can find it [here](https://github.com/bufolab/ChuckFacts/tree/mvp-rx)


## MVP-DAGGER-RX
* Dagger2 is used to apply DI pattern
* Presenter and UseCase are tested.

You can find it [here](https://github.com/bufolab/ChuckFacts/tree/mvp-dagger-rx)

## MVVM-DAGGER-RX
Comming
