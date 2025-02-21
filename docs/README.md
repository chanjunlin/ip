# ChinChin User Guide

![ChinChin](Ui.png)

## Introduction

Welcome to ChinChin. This simple and humble yet powerful CLI-based productivity tool helps you keep track of todos,
deadlines, and events effortlessly. Designed for users who prefer lightweight tools over complicated apps, ChinChin
makes managing tasks quick and easy.

## Main Feature - Add Tasks (Todo, Deadline, Event)

Command: ```todo```, ```deadline```, ```event```

Adds a new task to your list. This can be either a simple to-do, a deadline with a due date/time, or an event scheduled
for a specific time.

### Todo
Syntax: todo [task description]

Example: todo read a book

```expected output```
```
Oki, task added liao ✅:
[T] [ ] read a book
Now you got 1 task in the list.
```

### Deadline
Syntax: deadline [task description] /by [dd-MM-yyyy HHmm]

Example: deadline read a book /by 12-12-2025 1234

```expected output```
```
Oki, task added liao ✅:
[D] [ ] read a book
DEADLINE: Dec 12 2025 12:34 pm
Now you got 1 task in the list.
```

### Event
Syntax: event [task description] /from [dd-MM-yyyy HHmm] /to [dd-MM-yyyy HHmm]

Example: event read a book /from 12-12-2025 1234 /to 12-12-2025 2345

```expected output```
```
Oki, task added liao ✅:
[E] [ ] read a book (FROM: Dec 12 2025 12:34 pm TO: Dec 12 2025 11.45 pm)
Now you got 1 task in the list.
```

## Exit Program

Command: ```bye```,```goodbye```

Exits the program.

Example: `bye`

ChinChin would say goodbye and close the program.

```
expected output
```
```
Bye! Hope to see you again soon.
```

## Delete Tasks

Command: ```delete```

Deletes a task from your list.

Syntax: delete [task number]

Example: `delete [2]`

ChinChin would help to delete the task with the specified index.

```
expected output
```
```
Okay Boss, removed liao:
[T] [ ] read a book
```

## Find Task

Command: ```find```

Find the task(s) with the given keyword.

Syntax: find [key word]

Example: `find book`

ChinChin would help to collate all the tasks with the given keyword.

```
expected output
```
```
Here are some of the matches:
1. [T] [ ] read a book
```

## List All Tasks

Command:```list```

List all the tasks

Example: `list`

ChinChin would help to list out all the tasks, starting with `Todo`, `Deadline`, and finally `Event`.

```expected output```
```
Here are the tasks in your list:

[Todo 📝]:
1. [T] [ ] read a book

[Deadline ⏰]:
2. [D] [ ] read a book
DEADLINE: Dec 12 2025 12:34 pm

[Event 📅]:
No upcoming event le.

```

## Mark Task As Complete

Command: ```mark```

Mark a specific task as complete

Syntax: mark [task number]

Example: `mark 2`

ChinChin would help to mark specified task as complete.

```expected output```
```Orh, marked the task as done liao:
[T] [✔️] read a book
```

## Unmark Task As Incomplete

Command: ```unmark```

Unmark a specific marked task as incomplete

Syntax: unmark [task number]

Example: `unmark 2`

ChinChin would help to unmark specified task as complete.

```expected output```
```Orh, marked the task as undone liao:
[T] [✔️] read a book
```

## View Summary Of Tasks
Command: ```Summary```

Displays an overview of all your tasks categorized into their types and statuses.

Example: `summary`

ChinChin will help to display the summary of your tasks, which is the count of Todo, Deadline and Event

```expected output```
```Here's your summary:
📝Todos       : 1
⏰Deadlines   : 1
📅Event       : 0
```

## Show The Help For Each Commands
Command: ```help```

Displays the help for each of the command

Syntax: help [keyword]

Example: `help`

ChinChin would display all the available commands and what they do

```expected output```

Example: `help find`

ChinChin would advise you on how to use this command

```expected output```
```How to use 'FIND'? Just type
find [keyword]
```

## View Tasks On Date
Command: ```view```

Displays all the tasks on that specific date

Syntax: help [date]

Example: `view /on 12/12/2025`

ChinChin would display all the tasks on that date for you

```expected output```
```
Schedule for 2025-12-12:

[Deadlines]:
1. read a book - Due at 12:34 pm
```
