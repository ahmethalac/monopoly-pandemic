# 1F-Monopoly

## Group Members

| Name | Student ID |
| ---- | ---------- |
| Ahmet Feyzi Halaç | 21703026 |
| Ege Şahin | 21702300 | 
| Aybars Altınışık | 21601054 |
| Bulut Gözübüyük | 21702771 |
| Göktuğ Gürbüztürk | 21702383 |

## Project Description

Basic Monopoly Game with additional features whose theme is pandemic. This game will be played on World map.

### Rules
Rules inspired from original Monopoly Game (source:https://simple.wikipedia.org/wiki/Monopoly_(game)):
- Whenever you land on a region that no one owns, you can buy it from the bank. If you do not want to buy it the Banker sells it at an auction. (Not everyone plays by the auction rule). All of the prices for the region are on the board. Once you own the region, players must pay a rent if they are waiting on your region.
- If you land on a Chance region, you must pick a Chance card and do what it says.
- If you roll doubles (the same number on both dice) you get to roll again. If you roll doubles three times in a row you must stay in Quarantine region.
- When you pass Starting region, you collect some money from the bank.
- If you are going to mortgage region to the bank, you have to sell houses or special building back first. You can find the price of the mortgage on the back of the deed card. If the region is mortgage rent cannot be collected. To unmortgage region, you have to pay the mortgage.
- Bankruptcy. If you are bankrupt, you cannot pay someone rent or cannot pay a tax. If you declare bankruptcy you are done with the game.

Rules we created:
- You can make agreement with other players. These agreements are:
    - You can sell a region.
    - You can take any percentage of the rent of any players' region(s).
    - You can give money.
    - You can decide whether player on your region should pay a rent or not.
- Once at every two rounds, a random region will be infected by a virus.
- You can catch a virus from either Chance card or if you land the infected region.  
    - If you catch virus from Chance card, you will go to Quarantine region.  
    - If you catch virus from infected region, regions you landed will be infected for two rounds until you recover(in three rounds).
- If all players catch virus, they all go to Quarantine region and pay some money for their loss in quarantine days.
- There will be special regions. These regions are:
    - Las Vegas: If you land on this region, you throw dice against game, if you win you get money, you lose money otherwise.
    - more regions may be added...
