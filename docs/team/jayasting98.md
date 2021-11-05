---
layout: page
title: Jay Aljelo Saez Ting's Project Portfolio Page
---

### Project: ThunderCat

ThunderCat is a desktop application that can help NUS Year 2 Computer Science students who are doing group projects organise primarily the contacts of their group mates so that they can easily keep track of whom to contact for any group project. ThunderCat is optimised for students who can type fast, who prefer typing over using the mouse, and who are comfortable with command-line interface applications.

Given below are my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/#breakdown=true&search=jayasting98)

* **Enhancements implemented**:
  * Added ability to swap lists of certain model entities with lists of other model entities in the UI's list panel, allowing for easy re-use.
    * For example, swapping a list of persons on the list panel with a list of groups (which was the case for an earlier iteration of ThunderCat), or a list of groups on the list panel with a list of tasks.
  * Added UI representation of groups and tasks in the form of `GroupCard` and `TaskCard`.
  * Added ability for the application to keep track of an application state used to determine what to display on the UI and to determine whether a command can run or not in that state.
  * Added storage functionality for groups and tasks, and improved it for persons.
    * Optional fields like group descriptions, person addresses, or person tags are implemented such that they are excluded if they do not exist, saving storage space.
    * The stored group mates or persons list in each group does not store the details of the person, but rather just a reference, in order to save storage space.
  * Added `undo` command.

* **Project management**:
  * Managed releases `v1.2`, `v1.2.1`, and `v1.3` on GitHub
  * Maintained the issue tracker for `v1.1`, `v1.2`, `v1.2b`, `v1.3`, `v1.3b`, and `v1.4`
  * Reviewed pull requests

* **Documentation**:
  * User Guide:
    * Added the Usage (About the User Guide) section
    * Added documentation for the `undo` command [\#290](https://github.com/AY2122S1-CS2103T-W17-3/tp/pull/290) and the handling of the data file (saving, loading, editing, etc.) [\#177](https://github.com/AY2122S1-CS2103T-W17-3/tp/pull/177), [\#181](https://github.com/AY2122S1-CS2103T-W17-3/tp/pull/181), [\#200](https://github.com/AY2122S1-CS2103T-W17-3/tp/pull/200)
  * Developer Guide:
    * Added the non-functional requirements [\#26](https://github.com/AY2122S1-CS2103T-W17-3/tp/pull/26)
