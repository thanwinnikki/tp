---
layout: page
title: Wang Hong Yong's Project Portfolio Page
---

### Project: ThunderCat

ThunderCat (TC) is a desktop app designed for Year 2 Computing Students. You can manage contacts, organise them into project groups and manage your tasks according to the groups. The application is optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, ThunderCat can get your project groups' management tasks done faster than traditional GUI apps.

Given below are my contributions to the project.

**Added Features**
* **New Feature**: Added the ability to `add` a new `Group` to ThunderCat.
    * What it does: allows the user to `add` a new `Group` to ThunderCat specifying its name and description.
    * Justification: A key feature in the application in order access the `Group Information` page and use other core commands related to groups and tasks.
    * Highlights:
        * The `Name` must be present while `Description` is optional.
        * The `Description` optional field was included only after one iteration and adding it was marginally challenging as there were a lot of affected classes to integrate.

* **New Feature**: Added the ability to `remove` group members from existing groups while keeping their contact intact inside the ThunderCat.
    * What it does: allows the user to `remove` other contacts from an existing group with existing contact.
    * Justification: A key feature in the application for users to update their group contacts accordingly instead of having to create a new one from scratch everytime there are changes.

* **New Feature**: Added the ability to `edit` a `group` details.
    * What it does: allows the user edit the `Name` and the `Description` of the selected group.
    * Justification: As `Description` is set to be optional, this command not only serves its purpose to edit group details but also add `Description` to groups should they choose to leave it empty the first time.
    

* **Code contributed**:
  [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=asherhy&sort=groupTitle&sortWithin=title&since=2021-09-17&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false)


* **Project management**:
    * Managed releases `v1.1`, `v1.2`, `v.1,3`, `v.1,4` 4 major releases on GitHub.
    * Reviewed pull requests
    

* **Documentation**:
    * User Guide:
        * Added documentation for the features `remove` [\#83](https://github.com/AY2122S1-CS2103T-W17-3/tp/pull/83)
        * Added documentation for the features `editG` [\#184](https://github.com/AY2122S1-CS2103T-W17-3/tp/pull/184)
        * Added Command Summary [\#198](https://github.com/AY2122S1-CS2103T-W17-3/tp/pull/198)
        * Added Anchors, Edited Glossary, Cosmetic tweaks and changes to the About section [\#321](https://github.com/AY2122S1-CS2103T-W17-3/tp/pull/321) [\#322](https://github.com/AY2122S1-CS2103T-W17-3/tp/pull/322) [\#325](https://github.com/AY2122S1-CS2103T-W17-3/tp/pull/325)
        * Updated Glossary [\#349](https://github.com/AY2122S1-CS2103T-W17-3/tp/pull/349)
    * Developer Guide:
        * Added the user stories of this application [\#40](https://github.com/AY2122S1-CS2103T-W17-3/tp/pull/40).
        * Added Use case for `group` feature [\#149](https://github.com/AY2122S1-CS2103T-W17-3/tp/pull/149)
        * Updated Model section and added Feature `EditGroupCommand` under implementations [\#342](https://github.com/AY2122S1-CS2103T-W17-3/tp/pull/342)
        * Added Feature `JoinGroupCommand` under implementations with sequence and activity diagrams [\#363](https://github.com/AY2122S1-CS2103T-W17-3/tp/pull/363) [\#384](https://github.com/AY2122S1-CS2103T-W17-3/tp/pull/384)
