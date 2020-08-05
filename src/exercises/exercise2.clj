(ns exercises.exercise2
  "EXERCISE #2

  Congratulations, you are a new employee at a very large firm that
  specializes in hierarchies! They ask you to write a program that
  outputs a hierarchy tree based off of tuples (collections of two
  values) of names and the name of the person they report to. For
  instance, the tuple [\"B\" \"A\"] has the person B reporting to
  person A. Person A has [\"A\" nil] which means they report to no
  one (are at the top of the food chain). [Programming note: nil can
  be translated to null depending on language you are working
  with]. This data [[\"A\" nil], [\"B\" \"A\"]] would be drawn as

  A
  └B

  You will need to use the following unicode characters to draw the
  outputs. See below for test inputs and outputs.
  │ (U+2502),└ (U+2514)

  Note the following assumptions remain true for this problem:

  1) The first entry will always be the root entry, i.e. the employee
  will have no one they report to.
  2) There will only be one employee who reports to no one else in the
  tree.
  3) The order will not be randomized.

  Input:
  [[\"A\" nil]
  [\"B\" \"A\"]
  [\"C\" \"B\"]
  [\"D\" \"C\"]]

  Output:
  A
  └B
   └C
    └D

  Input:
  [[\"A\" nil]
  [\"B\" \"A\"]
  [\"C\" \"B\"]
  [\"D\" \"B\"]
  [\"E\" \"A\"]]

  Output:
  A
  └B
  │└C
  │└D
  └E

  Input:
  [[\"A\" nil]
  [\"B\" \"A\"]
  [\"C\" \"B\"]
  [\"D\" \"C\"]
  [\"E\" \"A\"]]

  Output:
  A
  └B
  │└C
  │ └D
  └E

  >>>>>Bonus: Can you add ├ (U+251C) such that:

  Input:
  [[\"A\" nil]
  [\"B\" \"A\"]
  [\"C\" \"B\"]
  [\"D\" \"B\"]
  [\"E\" \"A\"]]

  Output:
  A
  ├B
  │├C
  │└D
  └E

  Input:
  [[\"A\" nil]
  [\"B\" \"A\"]
  [\"C\" \"B\"]
  [\"D\" \"C\"]
  [\"E\" \"A\"]]

  Output:
  A
  ├B
  │└C
  │ └D
  └E"
  (:require [clojure.string :as s]))


(def input1 [["A" nil]
             ["B" "A"]
             ["C" "B"]
             ["D" "C"]])

(def input2 [["A" nil]
             ["B" "A"]
             ["C" "B"]
             ["D" "B"]
             ["E" "A"]])

(def input3 [["A" nil]
             ["B" "A"]
             ["C" "B"]
             ["D" "C"]
             ["E" "A"]])


(defn spaces
  "Create a string of length `space-number`."
  [space-number]
  (s/join "" (vec (map (fn [_] " ") (range space-number)))))


(defn has-children?
  "For a given vector `input`, confirm if there are any nodes which have
  the branches stemming from the `root`."
  [root input]
  (.contains (mapv last input) root))


(defn set-current-root-and-pass-count
  "Set the current root node `current-root` and the pass count `passes`
  based on the value being held in `cdr`."
  [cdr current-root passes]
  (reset! current-root (last (first @cdr)))
  (when (not= @current-root (last (first (rest @cdr))))
    (swap! passes inc)))


(defn display-new-branch
  "Display new branch from data in `cdr`."
  [cdr]
  (println (format
            (if (has-children? (last (first @cdr)) (rest @cdr))
              "├%s"
              "└%s")
            (ffirst @cdr))))


(defn ->tree
  "For the given `input`, create a hierarchical tree representation."
  [input]
  (let [root (ffirst input)
        cdr (atom (rest input))
        has-many-stemming-from-root? (-> (map last @cdr)
                                         frequencies
                                         (get root)
                                         (> 1))]
    (println root)
    (let [current-root (atom root)
          passes (atom 0)]
      (while (seq @cdr)

        (when (= root (last (first @cdr)))
          (reset! passes 0))

        (if (= @passes 0)
          (do
            (display-new-branch cdr)
            (set-current-root-and-pass-count cdr current-root passes))
          (do
            (println (format "%s%s%s"
                             (if has-many-stemming-from-root?
                               (str "|" (spaces (dec @passes)))
                               (spaces @passes))
                             (if (has-children? (last (first @cdr)) (rest @cdr))
                               "├"
                               "└")
                             (ffirst @cdr)))
            (set-current-root-and-pass-count cdr current-root passes)))
        (swap! cdr rest)))))

;;; sample output
