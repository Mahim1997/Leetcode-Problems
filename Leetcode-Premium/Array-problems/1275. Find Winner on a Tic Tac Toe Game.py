            
class Solution:
    def tictactoe(self, moves: List[List[int]]) -> str:
        def get_winner(sum_given):
            if sum_given == sum_a:
                return "A"
            if sum_given == sum_b:
                return "B"
            return "UNKNOWN"
        
        def get_row_winner(row_idx):
            sum_row = sum(board[row_idx][0:3])
            return get_winner(sum_row)
        
        def get_col_winner(col_idx):
            sum_col = sum([board[i][col_idx] for i in range(0, 3)])
            return get_winner(sum_col)

        board = [[0,0,0],[0,0,0],[0,0,0]]
        
        move_a = 1
        move_b = 100
        
        sum_a = move_a*3
        sum_b = move_b*3
        
        ## board fill-up
        for i, move in enumerate(moves):
            row = move[0]
            col = move[1]
            
            if i%2 == 0: ## 'A'
                board[row][col] = move_a    
            else:        ## 'B'
                board[row][col] = move_b
        
        print(board)
        
        for row in range(0, 3):
            row_winner = get_row_winner(row)
            if row_winner != "UNKNOWN":
                return row_winner
            
        for col in range(0, 3):
            col_winner = get_col_winner(col)
            if col_winner != "UNKNOWN":
                return col_winner
        
        ## Diagonal check
        sum_diag_1 = sum([board[i][i] for i in range(0, 3)])
        sum_diag_2 = sum([board[2-i][i] for i in range(0, 3)])
        
        if get_winner(sum_diag_1) != "UNKNOWN":
            return get_winner(sum_diag_1)
        
        if get_winner(sum_diag_2) != "UNKNOWN":
            return get_winner(sum_diag_2)
        
        
        ## No winner, need to check if board is remaining or not
        for row in range(0, 3):
            for col in range(0, 3):
                if board[row][col] == 0:
                    return "Pending"

        ## No remaining, hence draw
        return "Draw"
        