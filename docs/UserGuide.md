---
layout: page
title: User Guide
---

ThunderCat (TC) is a **desktop app designed for Year 2 Computing Students.** The application acts as an address book for contacts with *added support for group projects*. You can manage contacts, organise them into project groups and manage your tasks according to the groups. ThunderCat **does not** act as a messaging application, rather just a task management and contact management application specifically for group projects. The application is optimized for use via a **Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, ThunderCat can get your project groups' management tasks done faster than traditional GUI apps.

___
* Table of Contents
{:toc}
  

--------------------------------------------------------------------------------------------------------------------

## Usage

### Navigation

Use the [table of contents](#table-of-contents) to navigate the user guide easily. There will also be links in some sections linking to other relevant sections or to other websites which provide useful information.

### Formatting

There will be paragraphs that will have coloured backgrounds and with icons. These paragraphs highlight useful information:

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
This is a tip.
</div>

<div markdown="span" class="alert alert-info">:information_source: **Heading:**
This provides details.
</div>

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
This is a warning.
</div>

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `ThunderCat.jar` from [here](https://github.com/AY2122S1-CS2103T-W17-3/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for the application.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`list`** : Lists all persons.

   * **`add`**`n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01` : Adds a person named `John Doe` to the application's records.

   * **`delete`**`3` : Deletes the 3rd person shown in the current list.

   * **`clear`** : Deletes all persons.

   * **`exit`** : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

### Viewing help : `help`

Shows a message explaining how to access the help page.

![help message](images/helpMessage.png)

Format: `help`


### Adding a person: `add`

Adds a person to ThunderCat.

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A person can have any number of tags (including 0)
</div>

Examples:
* `add n/John Doe p/98765432 e/johnd@example.com a/John street, Block 123, #01-01`
* `add n/Betsy Crowe t/friend e/betsycrowe@example.com a/Newgate Prison p/1234567 t/criminal`

### Creating a group : `addG`

Creates a group in ThunderCat.

Format: `addG g/GROUP_NAME [d/DESCRIPTION]`

Examples:  
* `addG g/CS2103 d/Project group Y2S1`
* `addG g/Family`

### Viewing all groups: `groups`

Shows a list of all groups in ThunderCat.

Format: `groups`

### Listing all persons : `list`

Shows a list of all persons in ThunderCat.

Format: `list`

### Editing a person : `edit`

Edits an existing person in ThunderCat.

Format: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`

* Edits the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the person will be removed i.e adding of tags is not cumulative.
* You can remove all the person’s tags by typing `t/` without
    specifying any tags after it.

Examples:
* `edit 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st person to be `91234567` and `johndoe@example.com` respectively.
* `edit 2 n/Betsy Crower t/` Edits the name of the 2nd person to be `Betsy Crower` and clears all existing tags.

### Editing a group: `editG`

Edits an existing group in ThunderCat.

Panel where this command can be called :
* Group Information panel

Format: `editG GROUP_INDEX [n/NAME] [d/DESCRIPTION]`

* The group with the specified `GROUP_INDEX` will be edited.
* The `GROUP_INDEX` refers to the index number shown in the last displayed group list.
* The index **must be a positive integer** 1, 2, 3, …​

Example:
* `editG 1 n/ES2660 d/Communications Mod`

### Locating persons by name: `find`

Finds persons whose names contain any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only the name is searched.
* Only full words will be matched e.g. `Han` will not match `Hans`
* Persons matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:
* `find John` returns `john` and `John Doe`
* `find alex david` returns `Alex Yeoh`, `David Li`<br>
  <br>
  ![result for 'find alex david'](images/findAlexDavidResult.png)

### Deleting a person : `delete`

Deletes the specified person from the ThunderCat (removes all groups related to this person automatically).

Format: `delete INDEX`

* Deletes the person at the specified `INDEX` and removes it from all related groups automatically.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd person in the ThunderCat.
* `find Betsy` followed by `delete 1` deletes the 1st person in the results of the `find` command.
* `mates 2` followed by `delete 1` deletes the 1st person in the 2nd group.

### Locating groups by name: `findG`

Find groups whose names contain any of the given keywords.

Format: `findG KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `cs2103T` will match `CS2103T`
* The order of the keywords does not matter. e.g. `Favourite Group` will match `Group Favourite`
* Only the name is searched.
* Only full words will be matched e.g. `CS2103` will not match `CS2103T`
* Persons matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `CS2103T` will return `CS2103T Friends`, `CS2103T Tutors`

Examples:
* `findG CS2103T` returns `CS2103T Mates` and `CS2103T Tutors`
* `findG CS2103T Mates` returns `CS2103T Project Mates`, `Lectures CS2103T`
  

  ![result for 'find CS2103T Mates'](images/findG_CS2103T_Mates_Result.png)

### Deleting a group : `deleteG`

Deletes a specified group from ThunderCat.

Format: `deleteG g/GROUP_INDEX`

* Deletes the group at the specified `GROUP_INDEX`.
* The group index refers to the group number shown in the displayed group list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `deleteG g/2` deletes the 2nd group in ThunderCat.

### Adding multiple people to a group : `joinG`

Adds multiple specified people to a specified group.

:bulb: Panel where this command can be called :
* Home panel

Format: `joinG p/PERSON_INDEX_1, p/PERSON_INDEX_2, …​ g/GROUP_INDEX`

* Adds to a group specified at `GROUP_INDEX`, multiple people specified at `PERSON_INDEX_1, PERSON_INDEX_2, …​`.
* The group index refers to the group number shown in the **displayed group list**.
* The person index refers to the person index number show in the **displayed person list**.
* Group index **must be a positive integer** 1, 2, 3, …​
* Person index **must be a positive integer** 1, 2, 3, …​

Examples:
* `joinG p/1 p/2 p/3 g/2` adds persons indexed 1, 2, 3 to group 2.

### Listing all group mates and tasks in a group: `group`

Lists out all the group mates and tasks in a group.

Format: `group GROUP_INDEX`

* The group mates and tasks in the group at the specified `GROUP_INDEX` are listed out.
* The `GROUP_INDEX` refers to the index number shown in the last displayed group list.
* The index **must be a positive integer** 1, 2, 3, …​

Example:
* `group 1`

### Remove a person from the current group: `remove`

Remove the person from the group that ThunderCat is displaying.

Panel where this command can be called :
* Group Information panel

Format: `remove PERSON_INDEX`

* The group mates with the specified `PERSON_INDEX` will be removed from the group.
* The `PERSON_INDEX` refers to the index number shown in the last displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​

Example:
* `remove 2`

### Adding a task to the current group: `addT`

Adds a task to the group that ThunderCat is displaying.

Format: `addT d/TASK_DESCRIPTION`

Example:
* `addT d/Prepare pitch`

### Delete a task from the current group: `deleteT`

Delete the task from the group that ThunderCat is displaying.

Format: `deleteT TASK_INDEX`

* The task with the specified `TASK_INDEX` will be removed from the current group.
* The `TASK_INDEX` refers to the index number shown in the displayed task list.
* The index **must be a positive integer** 1, 2 ,3, …​

### Marking a task as done: `done`

Marks the specified task in the group as done.

Format: `done TASK_INDEX`

* The task with the specified `TASK_INDEX` will be marked as done.
* The `TASK_INDEX` refers to the index number shown in the task list.
* The index **must be a positive integer** 1, 2, 3, …​

Example:
* `done 2`

### Clearing all entries : `clear`

Clears all entries from ThunderCat.

Format: `clear`

### Exiting the program : `exit`

Exits ThunderCat.

Format: `exit`

### Saving the data

The data is saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Loading the data

The data is loaded automatically from the hard disk when the application starts. There is no need to load the data manually.

If there is no data file to load, such as when the application has just been downloaded and has not been run yet or when the data file is deleted, then sample records are loaded instead.

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
Use [`clear`](#clearing-all-entries--clear) to easily and quickly remove the sample records if desired.
</div>

If the data file has an invalid format, such as after editing the file wrongly, then the application will discard all the data and start with an empty data file on the next run. This is **irreversible**.

### Editing the data file

The data is saved as a JSON file called `records.json` located in `[JAR file location]/data`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If any of the changes you make to the data file makes it have an invalid format, the application will discard all the data and start with an empty data file on the next run. This is **irreversible**.
</div>


--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data from the home folder of the application in your original computer.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format | Examples | Home Panel | Group Information Panel
--------|---------|---------|---------|---------
**Add person** | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​` | `add n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 t/friend t/colleague` | :white_check_mark: | :white_check_mark:
**Clear all** | `clear` | | :white_check_mark: | :white_check_mark:
**Delete person** | `delete INDEX` | `delete 3` | :white_check_mark: 
**Edit person** | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`|`edit 2 n/James Lee e/jameslee@example.com` | :white_check_mark: | 
**Find person** | `find KEYWORD [MORE_KEYWORDS]` | `find James Jake` | :white_check_mark: |
**List all persons** | `list` | | :white_check_mark: | :white_check_mark:
**Create Group** | `addG n/GROUP_NAME [d/DESCRIPTION]` | `addG CS2103 d/Project Group` | :white_check_mark: | :white_check_mark:
**Show all Groups** | `groups` | | :white_check_mark: | :white_check_mark:
**Show Group information** | `group GROUP_INDEX` | `group 2` | :white_check_mark:
**Edit Group** | `editG GROUP_INDEX [n/NAME] [d/DESCRIPTION]` | `editG 2 n/CS2103T d/New project group for Y2S1` | :white_check_mark: 
**Find Group** | `findG KEYWORD [MORE_KEYWORDS]` | | :white_check_mark:
**Delete Group** | `deleteG g/GROUP_INDEX` | `deleteG g/2` | :white_check_mark: 
**Join Group** | `joinG p/PERSON_INDEX_1, p/PERSON_INDEX_2, …​ g/GROUP_INDEX` | `joinG p/1 p/2 g/2` | :white_check_mark: | 
**Remove person from Group** | `remove PERSON_INDEX` | `remove 2` | | :white_check_mark:
**Add task to group** | `addT d/DESCRIPTION` | `addT d/read book` | | :white_check_mark:
**Mark task as done** | `done TASK_INDEX` | `done 3`  | | :white_check_mark:
**Delete task in group** | `deleteT TASK_INDEX` | `deleteT 1`  | | :white_check_mark:
**Exit application** | `exit` | | :white_check_mark: | :white_check_mark:
**Help** | `help`| | :white_check_mark: | :white_check_mark:

--------------------------------------------------------------------------------------------------------------------

## Glossary

* **Home Panel**: The home page where the lists of persons and groups are displayed.<br>
<br>![Home Panel](images/HomePanel.png)


* **Group Information Panel**: The group information page where the lists of group members and tasks are displayed.<br>
<br>![Group Information Panel](images/GroupInformationPanel.png)


* **JSON**: JSON is an open standard file format and data interchange format that uses human-readable text to store and transmit data objects consisting of attribute–value pairs and arrays (or other serializable values).
