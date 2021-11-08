---
layout: page
title: Nguyen Thuan Hung's Project Portfolio Page
---

### Project: ThunderCat

ThunderCat is a desktop application that can help NUS Year 2 Computer Science students who are doing group projects organise primarily the contacts of their group mates so that they can easily keep track of whom to contact for any group project. ThunderCat is optimised for students who can type fast, who prefer typing over using the mouse, and who are comfortable with command-line interface applications.

Given below are my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=hungkhoaitay&sort=groupTitle&sortWithin=title&since=2021-09-17&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false)

* **Enhancements implemented**:
  * Added additional UI panel to render two lists at the same time. (For example, list of persons and list of groups) [\#174](https://github.com/AY2122S1-CS2103T-W17-3/tp/pull/174)
  * Added UI panel's description to show what is in the list currently (List of persons or list of tasks). Added UI representation of application states in `StatusBarFooter` to show current state of the application (For example, `HOME` state or `Group CS2103T` state) [\#208](https://github.com/AY2122S1-CS2103T-W17-3/tp/pull/208)
  * Created new rounded-corners UI with new theme, reordering components inside [\#289](https://github.com/AY2122S1-CS2103T-W17-3/tp/pull/289)
  * Fix bug with testing related to `GroupBuilder`. Implement new `GroupBuilder` method in order to create new `Group` objects every time it is invoked. Therefore, the testings of different classes won't affect each others [\#121](https://github.com/AY2122S1-CS2103T-W17-3/tp/pull/121)
  * Expand `TaskCard`'s name to 70 characters to help users to add task's name comfortably 
  * Added `DeleteGroup`, `FindGroup` commands. [\#118](https://github.com/AY2122S1-CS2103T-W17-3/tp/pull/118) [\#56](https://github.com/AY2122S1-CS2103T-W17-3/tp/pull/56)
  * Enhance `DeletePerson` command [\#73](https://github.com/AY2122S1-CS2103T-W17-3/tp/pull/73)

* **Contributions to team-based tasks**:
  * Setting up the GitHub team org/repo.
  * Setting up tools: PlantULM, Soft Wrapping

* **Project management**:
  * Managed releases `v1.1`, `v1.2`, `v.1,3` 3 major releases on GitHub.
  * Reviewed pull requests 
  * Update Product Website [\#333](https://github.com/AY2122S1-CS2103T-W17-3/tp/pull/333)

* **Documentation**:
  * User Guide:
    * Added the Usage (About the User Guide) section
    * Added documentation for the `findG` and `delete` and commands [\#175](https://github.com/AY2122S1-CS2103T-W17-3/tp/pull/175)
    * Unified the screenshot in UG [\#197](https://github.com/AY2122S1-CS2103T-W17-3/tp/pull/197) 
    * Initiated Command Summary Table [\#27](https://github.com/AY2122S1-CS2103T-W17-3/tp/pull/27)
    * Annotate the difference (Before-After) and component's name in UG screenshots [\#359](https://github.com/AY2122S1-CS2103T-W17-3/tp/pull/359) [\#350](https://github.com/AY2122S1-CS2103T-W17-3/tp/pull/350)
  * Developer Guide:
    * Update UI diagram for new components namely `Listable`, `GroupCard`, `TaskCard` [\#360](https://github.com/AY2122S1-CS2103T-W17-3/tp/pull/360)
    * Added the adding list panel and making rounded-corners UI instructions [\#379](https://github.com/AY2122S1-CS2103T-W17-3/tp/pull/379) 
    * Initiated use cases [\#30](https://github.com/AY2122S1-CS2103T-W17-3/tp/pull/30)

* **Contributions beyond the project team:**:
  * Sharing information related to installing Graphviz for Mac M1 in the forum [\#218](https://github.com/nus-cs2103-AY2122S1/forum/issues/218)
  * Helping teammate installing and setting up Graphviz and PlantULM via call.
  * Helping teammate setting up GitHub's remotes (fetch and push) via call.
