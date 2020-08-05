# exercises 1 & 2

## Usage

You'll need leiningen installed and you're set.

Then look into the namespaces: `exercises.exercise1` & `exercises.exercise2`.

You can see the output of the exercises under the functions, within
comment blocks for instant opinion forming, tutting and liberal
eyebrow raising. Enjoy!


## (My own) Critique (of my own code)

What could be done better?


In exercise2 in particular: I've used atoms for holding values. It
would've been better if I just kept passing the input values around
and access it via `nth` (for which I would have had to keep count up
to which point I processed data), without storing the temporary
values.

I figured that from the code and my write up here, it'll be enough to
see if I can code.

Also, instead of using positional arguments, I could have used maps to
pass in as input, because that removes the need to keep the mental
note about the positions of the arguments. Again, I figured that this
is a just a sample toy exercise.
