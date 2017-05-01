# Card Game - Portfolio #3

Contributors:

Brett Wilhelm


# Game Title: Round-a-Bout

Values on card:

topLeft = Name
topRight = Type (Hero or Boost)
bottomLeft = HP (Depletes every other turn along with Value, removes card from game at 0)
bottomMid = Player # of owner
bottomRight = Value (Used for Hero battles and Life damage)

Keyboard Keys:

play() = Click on either the deck or unused zones
endTurn() = Press the enter/return key
quit() = Press the escape key,

Ruleset:

At the beginning of every turn, click on the deck to reveal a card. You cannot continue your turn until you do this (although you can still quit the game or end your turn)

After revealing a card, it is displayed on top of your deck. You may then choose to either a) play that card onto its corresponding segment of the playfield or b) play a card that you didn't use last turn (the top card in your unused pile)

You cannot play a card from either area if your card's value (Bottom-right of card) is less than the corresponding value of an opponent's card that takes up the same space. If you have a boost card in your player boost zone, this value is added to the value of your Hero (Beware, your opponent's boost card will be subtracted from this when calculating the winner of a Hero battle)

At the end of your opponent's next turn, every card you control on the play field lose a single point of HP (Bottom-left of card). If this value hits zero, the card is removed from play

If you control the current Hero on the field, at the end of your opponent's next turn they will lose HP calculated by subtracting their current HP by the Hero's value stat (Bottom-right of card)

Play until one of you reaches 0 HP, this decides the winner

If both players run out of cards before a winner is decided, the game ends