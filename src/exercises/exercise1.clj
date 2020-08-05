(ns exercises.exercise1
  "EXERCISE #1

  Given the coins 1, 5, 10, 25, 100, 200, what is the minimum number
  of coins needed to make change for x, and what coins do you use? For
  instance if you need to make change for 53 cents you need five
  coins, two quarters (25 cents), and three pennies (1 cent). You have
  an infinite number of coins to dispense change with (aren't you
  special).

  Input:
  653
  63
  132

  >>>>>Bonus: You now have a special 53 cent coin in your arsenal. How
  do the answers above change?")


(defn call-and-calc
  "A helper function, that calls `your-fn` on the result of `input` &
  `amount`."
  [your-fn input amount]
  (let [result (- input amount)]
    (print amount" ")
    (when (> result 0)
      (your-fn result))))

;;; sample output
;;
;; exercises.exercise1> (work-out-the-change1 653)
;; 200  200  200  25  25  1  1  1  nil
;; exercises.exercise1> (work-out-the-change1 63)
;; 25  25  10  1  1  1  nil
;; exercises.exercise1> (work-out-the-change1 132)
;; 100  25  5  1  1  nil

(defn work-out-the-change1
  "For a given `input`, work out the change that should be returned in
  coins of 1, 5, 10, 25, 100 & 200 cents."
  [input]
  (let [->change (partial call-and-calc work-out-the-change1 input)]
    (condp <= input
      200 (->change 200)
      100 (->change 100)
      25  (->change 25)
      10  (->change 10)
      5   (->change 5)
      1   (->change 1)
      :dunno)))


(defn work-out-the-change2
  "For a given `input`, work out the change that should be returned in
  coins of 1, 5, 10, 25, 100 & 200 cents.

  Almost the same as above, except the coin of 53 cents was introduced
  as the answer to the bonus part of the question, as noted in the ns
  doc string."
  [input]
  (let [->change (partial call-and-calc work-out-the-change2 input)]
    (condp <= input
      200 (->change 200)
      100 (->change 100)
      53  (->change 53)
      25  (->change 25)
      10  (->change 10)
      5   (->change 5)
      1   (->change 1)
      :dunno)))


;;; sample output
;;
;; exercises.exercise1> (work-out-the-change2 653)
;; 200  200  200  53  nil
;; exercises.exercise1> (work-out-the-change2 63)
;; 53  10  nil
;; exercises.exercise1> (work-out-the-change2 132)
;; 100  25  5  1  1  nil
