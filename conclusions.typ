#show raw: box.with(fill: silver, radius: 2pt, inset: (x: 2pt), outset: (y: 2pt))

#set heading(numbering: "I.")
#set par(first-line-indent: 2em)

#align(center)[
  #text(weight: "bold", size: 24pt)[
    Computational Geometry\
    First Project Conclusions
  ]

  \

  #text(size: 14pt)[
    #h(1fr) Gian Peña #h(2fr) Henrique Pitta Laranjinha #h(1fr)
  ]
]

\
\

= Challenges Faced
One significant challenge we faced was in choosing a specific method in which to render the car. The rules were one `Rectangle` for the body of the car, one `Triangle` for the nose, and four smaller `Rectangle`s for the car's wheels.

The way we ended up choosing to implement this was by basing all polygons rendered for the car on a single point within the car's body (to be precise, the centroid of the car body's rectangle). Another issue we encountered was with the rotations. There was some initial confusion on our part with the outputs we were seeing because we had forgotten that the _y-_axis values increased going _down_, not up.

#figure(
  image("demo.png"),
  caption: [ An image of the `Car` class's code, along with the application's GUI open with the car rendered. ],
  supplement: none
)
= What We Learned
We learned that rotating a shape around an arbitrary point amounts to translating it to the origin, rotating it, and translating it back. Because screen _y-_values increase going down, positive angles turn clockwise on screen. We also learned how to compose a larger object, the `Car`, out of the geometric objects from class, and to move or turn it by applying the same transformation to every part. Finally, we learned to animate in Java with a Swing `Timer`, moving the car a small step and calling `repaint()` on every tick.
