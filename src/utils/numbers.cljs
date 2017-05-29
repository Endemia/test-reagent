(ns utils.numbers)

(defn countFromNumber [number]
  (case (last (str number))
    "1" (str number "st")
    "2" (str number "nd")
    "3" (str number "rd")
    (str number "th")
    )
  )