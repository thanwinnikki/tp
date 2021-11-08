---
layout: page
title: Nikki Than Win's Project Portfolio Page
---

### Project: Thunder Cats

ThunderCat (TC) is a desktop app designed for Year 2 Computing Students. The application acts as an address book for contacts with added support for group projects. You can manage contacts, organise them into project groups and manage your tasks according to those groups.

ThunderCat also acts as a tool for Year 2 Computing Students to build up their confidence in using a Command Line Interface (CLI) to navigate applications and manipulating JSON files - a popular data file format widely used in Computing.

Given below are my contributions to the project.

**Added Features**
* _New Feature_ : <br>
  Added ability for Thunder Cat to support `group` functionalities of `joinG`, `editG`, `remove`, `deleteG`.
* _New Feature_ : <br>
  Added ability to add multiple people to an existing group in ThunderCat through the `joinG` command.
  * What it does: Allows the User to add multiple people to a single group in a single go.
  * Justification: This feature prevents Users from having to keep using a single command to add a group mate one at a time. This would make the task of adding group mates much faster and intuitive.  
* _New Feature_ : <br>
  Added ability for Thunder Cat to support `task` functionalities of `addT`, `doneT` and `deleteT`.
* _New Feature_ : <br>
  Added ability for users to delete a `task` from a specified group in ThunderCat through the `deleteT` command.
  * What it does: Deletes a task from inside a group.
  * Justification: This feature allows Users to remove tasks from the group information view if needed. In the case that users no longer need to keep track of the task, or they need to ammend the description of the task. They can simply just delete a task and add it again.
    
**Added Enhancements**
* Added JavaDoc comments to all existing classes and methods in command classes. [\370](https://github.com/AY2122S1-CS2103T-W17-3/tp/pull/370)
* Wrote comprehensive tests for all the features that I have implemented.


**Contribution to team-based tasks**
* Ideated the idea to have a group project based application. With the focus on allowing users to practice CLI and manipulating JSON files.
* Specifically added the `Group`, `UniqueGroupList` and `Task` classes to lay the groundwork for my team to add further functionalities to groups and tasks.
* Team-mates were able to work with a black box and trust that I would be able to implement what they needed in order to implement functions and commands for `group` and `task`.
* Establish meeting agenda for each meeting so that our team meetings are effective and we stay on task.  
* Finalised the name, icon and display of the application.



* **Code contributed**:
  [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=thanwin&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=thanwinnikki&tabRepo=AY2122S1-CS2103T-W17-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=functional-code~other&authorshipIsBinaryFileTypeChecked=false)

* **Project management**:
  * Managed releases `v1.2`, `v1.2.1`, `v1.3`, `v1.4` on GitHub
  * Reviewed pull requests
  * Regularly created issues on Github to review potential improvements and bugs of the application.  

* **Documentation**:
    * User Guide:
        * Added documentation for the features `deleteG`, `addG` in UG. [/#36](https://github.com/AY2122S1-CS2103T-W17-3/tp/pull/36)
        * Added documentation for the features `joinG`, `deleteT` in UG.[/#183](https://github.com/AY2122S1-CS2103T-W17-3/tp/pull/183)
        * Based off peer testing, amended the bug in the UG.[/296](https://github.com/AY2122S1-CS2103T-W17-3/tp/pull/296)
    * Developer Guide:
        * Added the target user profile of this application.
        * Added use case to Developer Guide for adding tasks to a group [\#146](https://github.com/AY2122S1-CS2103T-W17-3/tp/pull/146)
        * Added Appendix Section to the DG. This includes the use cases and manual testing. [\#390](https://github.com/AY2122S1-CS2103T-W17-3/tp/pull/390)
