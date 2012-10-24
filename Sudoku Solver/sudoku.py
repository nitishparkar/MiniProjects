'''
 A program that solves sudoku puzzle
 Check test_input.txt for input format
'''

DIM = 9
matrix = {}
input_file = 'test_input.txt'

def get_input():
	f = open(input_file, 'r')
	count = 0
	
	for line in f:
		num = int(line)
		if num == 0:
			matrix[count] = list(range(1, DIM + 1))
		else:
			matrix[count] = [num]
		count += 1
	print(count)


def print_matrix():
	i = 0
	for key in matrix.keys():
		if i % DIM == 0:
			print()
		elif i % 3 == 0:
			print('|', end=' ')
		if i % 27 == 0:
			print('-' * 21)
		if len(matrix[key]) == 1:	
			print(matrix[key][0], end=' ')
		else:
			print('?', end=' ')
		i += 1
	print()
	print('-' * 21)


def solved():
	for x in range(0, DIM ** 2 , DIM):
		for y in range(DIM):
			if len(matrix[x+y]) != 1:
				return False
	return True


def print_raw():
	i = 0
	for key in matrix.keys():
		if i % DIM == 0:
			print()
		if len(matrix[key]) == 1:	
			print(str(matrix[key][0]) + ' '*(DIM-1), end=' ')
			#print(matrix[key][0], end=' ')
		else:
			print(''.join(map(str, matrix[key])) + ' '*(DIM-len(matrix[key])), end=' ')
		i += 1


def filter_matrix(row, column):
	digit = matrix[row+column][0]
	
	# elements in the same row:
	for x in range(row, row + DIM):
		if x != (row+column):
			try:
				matrix[x].remove(digit)
			except ValueError:
				# digit is not present inside matrix[x], happens!
				pass 

	# elements in the same column:
	for x in range(0, DIM ** 2 , DIM):
		if x != row:
			try:
				matrix[x + column].remove(digit)
			except ValueError:
				# digit is not present inside matrix[x + column], happens!
				pass

	# elements in the same cell:
	minrow = row // 27 * 27
	maxrow = minrow + 27
	mincol = column // 3 * 3
	maxcol = mincol + 3

	for x in range(minrow, maxrow, DIM):
		for y in range(mincol, maxcol):
			if x == row and y == column:
				pass
			else:
				try:
					matrix[x + y].remove(digit)
				except ValueError:
					# digit is not present inside matrix[x + y], happens!
					pass


def find_loner(row, column):
	elements = matrix[row + column]
	se = set(elements)

	for x in range(row, row + DIM):
		if x != (row+column):
			try:
				se -= set(matrix[x])
			except ValueError:
				pass

	if len(se) == 1:
		matrix[row + column] = list(se)
		return

	se = set(elements)

	for x in range(0, DIM ** 2 , DIM):
		if x != row:
			try:
				se -= set(matrix[x + column])
			except ValueError:
				pass

	if len(se) == 1:
		matrix[row + column] = list(se)
		return

	se = set(elements)

	minrow = row // 27 * 27
	maxrow = minrow + 27
	mincol = column // 3 * 3
	maxcol = mincol + 3

	#print(digit, row, minrow, maxrow, column, mincol, maxcol)
	for x in range(minrow, maxrow, DIM):
		for y in range(mincol, maxcol):
			if x == row and y == column:
				pass
			else:
				try:
					se -= set(matrix[x + y])
				except ValueError:
					pass

	if len(se) == 1:
		matrix[row + column] = list(se)


def process():
	for x in range(0, DIM ** 2 , DIM):
		for y in range(DIM):
			if len(matrix[x+y]) == 1:
				#print(matrix[x+y][0])
				filter_matrix(x, y)
			else:
				find_loner(x, y)

def main():
	get_input()
	print('Puzzle:')
	print_matrix()
	limit = 10
	while not solved() and limit > 0:
		process()
		limit -= 1

	if solved():
		print('\n\nSolution:')
		print_matrix()
	else:
		print('\n\nCould not solve the puzzle\nPartial solution:')
		print_raw()


if __name__ == '__main__':
    main()