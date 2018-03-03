from math import ceil
from os import path

from matplotlib import pyplot as plt
from matplotlib.lines import Line2D
import numpy as np


COLOR_ORDER = 'rbgkmcy'


class DataCategory(object):
	def __init__(self, formal, short, unit, labels=None):
		self.formal_name = formal
		self.short_name = short;
		self.unit = unit
		self.has_subcategories = False
		self.values = list()
		self.labels = labels
		if labels is None:
			self.labels = [self.formal_name]
	
	def __str__(self):
		return 'DataCategory {}'.format(self.formal_name)
	
	def add_value(self, value):
		if len(self.values) == 0 and type(value) is list:
			for _ in range(len(value)): self.values.append(list())
		if not type(value) is list:
			self.values.append(value)
			return
		self.has_subcategories = True
		for num, item in enumerate(value):
			self.values[num].append(value)

	def label(self):
		return self.formal_name

	def short(self):
		return self.short_name

	def unit(self):
		return self.unit

	def plot(self):
		if self.has_subcategories:
			for num, value_list in enumerate(self.values):
				plt.plot(TIMESTAMPS, value_list, COLOR_ORDER[num] + '-', label=self.labels[num])
		else:
			plt.plot(TIMESTAMPS, self.values, COLOR_ORDER[0] + '-', label=self.labels[0])


# Input your data categories here
TIMESTAMPS = list() # The x-axis
CATEGORIES = [
	DataCategory('Lift Position', 'lp', 'Encoder Pulses'),
	DataCategory('Lift Current', 'lc', 'Amperes', ['One', 'Two']),
	DataCategory('Lift Output Percent', 'lo', 'Percent'),
	DataCategory('Drive Motor Output Percent', 'do', 'Percent', ['Front Left', 'Front Right', 'Back Left', 'Back Right']),
	DataCategory('Drive Motor Output Current', 'dc', 'Amperes', ['Front Left', 'Front Right', 'Back Left', 'Back Right']),
	DataCategory('Drive Motor Encoder Position', 'dp', 'Encoder Pulses', ['Front Left', 'Front Right', 'Back Left', 'Back Right']),
	DataCategory('Drive Motor Encoder Velocity', 'dv', 'Meters per 100ms', ['Front Left', 'Front Right', 'Back Left', 'Back Right']),
	DataCategory('Intake Output Percent', 'ip', 'Percent', ['Left', 'Right']),
	DataCategory('Intake Output Current', 'ic', 'Amperes', ['Left', 'Right']),
	DataCategory('Intake Solenoid State', 'is', 'Forward = 1.0, Reverse = -1.0)'),
	DataCategory('Limelight Connected', 'lm', 'Boolean (True = 1.0, False = 0.0)'),
	DataCategory('Vision Target Exists', 've', 'Boolean (True = 1.0, False = 0.0)'),
	DataCategory('Vision Target Angle', 'vt', 'Degrees'),
	DataCategory('Vision Target Area', 'va', 'Percent (of Image Area)'),
	DataCategory('LED Color', 'led', 'Color Value (Between 0 and 255)', ['Red', 'Green', 'Blue']),
	DataCategory('CAN Bus Utilization', 'can', 'Percent'),
	DataCategory('Brown Out', 'bo', 'Boolean (True = 1.0, False = 0.0)'),
	DataCategory('Battery Voltage', 'bv', 'Volts'),
	DataCategory('Pressure', 'psi', 'psig')
]
# End of data category section


LOOKUP_DICT = {b.label(): b for b in CATEGORIES} # A dictionary to get DataCategory from its formal name


def populate_categories(file_path):
	file_path = path.abspath(file_path)
	with open(file_path, 'r') as ctx:
		for line in ctx:
			if line.startswith('Timestamp'):
				TIMESTAMPS.append(float(line.split(': ')[1]))
			else:
				split_middle = line.split(': ')
				if len(split_middle) <= 1: continue
				key = split_middle[0].replace('\t', '').strip()
				value_str = split_middle[1].replace('[', '').replace(']', '').strip()
				values = [float(b.strip()) for b in value_str.split(',')]
				if key in LOOKUP_DICT:
					LOOKUP_DICT[key].add_value(values[0] if len(values) == 1 else values)


def data_category_from_shorthand(short):
	for category in CATEGORIES:
		if category.short() == short:
			return category
	return None


def run_with_user_input():
	file_name = input('Path to the data log: ')
	print('Populating data containers...')
	populate_categories(file_name)
	global TIMESTAMPS
	TIMESTAMPS = np.array(TIMESTAMPS) - TIMESTAMPS[0]
	print('Done populating containers')
	print('Enter the shorthand titles, comma separated, of the categories for which you would like to generate graphs.')
	[print('{} ({})'.format(a.label(), a.short())) for a in CATEGORIES]
	selected_categories = input('Select one (or more) categories: ').strip().split(',')
	selected_categories = [a.strip().lower() for a in selected_categories]
	data_categories = list()
	for selected in selected_categories:
		category = data_category_from_shorthand(selected)
		if category is None: continue
		data_categories.append(category)
	print('Generating plots...')
	do_plots(data_categories)


def do_plots(categories_to_plot):
	for category in categories_to_plot:
		category.plot()
		plt.title(category.label())
		plt.show()
		print('Would you like to save the plot of {}? '.format(category.label()))
		save = input('Save? (y/n)')
		if save.lower().strip() == 'y':
			plt.savefig(category.label())

if __name__ == '__main__':
	run_with_user_input()
