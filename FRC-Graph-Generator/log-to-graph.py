import os
from os import path

from matplotlib import pyplot as plt


X_AXIS_LABEL = 'Seconds (Since RoboRIO power-up)'
Y_AXIS_LABELS = {
    'lp': 'Encoder Pulses',
    'lc': 'Amperes',
    'lo': 'Percent',
    'do': 'Percent',
    'dc': 'Amperes',
    'dp': 'Encoder Pulses',
    'ip': 'Percent',
    'ic': 'Amperes',
    'is': 'Boolean (True = 1.0, False = 0.0)',
    'lm': 'Boolean (True = 1.0, False = 0.0)',
    've': 'Boolean (True = 1.0, False = 0.0)',
    'vt': 'Degrees',
    'va': 'Percent (of Image Area)',
    'led': 'Color Value (Between 0 and 255)'
}

GRAPH_TYPES = {
    'lp': 'Lift Position',
    'lc': 'Lift Current',
    'lo': 'Lift Output Percent',
    'do': 'Drive Motor Output Percent',
    'dc': 'Drive Motor Output Current',
    'dp': 'Drive Motor Encoder Position',
    'ip': 'Intake Output Percent',
    'ic': 'Intake Output Current',
    'is': 'Intake Solenoid States',
    'lm': 'Limelight Connected',
    've': 'Vision Target Exists',
    'vt': 'Vision Target Angle',
    'va': 'Vision Target Area',
    'led': 'LED Color'
}

INVERTED_GRAPH_TYPES = {b: a for a,b in GRAPH_TYPES.items()}

file_name = input('Path to data log: ').strip()
file_path = path.abspath(file_name)

print('Which graph should be generated? Use the shorthand labels given in parentheses as your input.')
for key,value in GRAPH_TYPES.items():
    print('{} ({})'.format(key, value))

graph_type = ''
while not graph_type in GRAPH_TYPES:
    graph_type = input('Graph to generate: ').replace('(', '').replace(')', '').strip().lower()

modifier = ''
if graph_type in ('do', 'dc'):
    print('Front Left (fl)')
    print('Front Right (fr)')
    print('Back Left (bl)')
    print('Back Right (br)')
    while not modifier in ('fr', 'fl', 'br', 'bl'):
        modifier = input('Choose a motor: ')
if graph_type in ('dp', 'ip', 'ic'):
    print('Left (l)')
    print('Right (r)')
    while not modifier in ('l', 'r'):
        modifier = input('Choose a side: ')

timestamps = list()
y_axis = list()
with open(file_path, 'r') as ctx:
    for line in ctx:
        if line.startswith('Timestamp'):
            timestamps.append(float(line.split(': ')[1]))
        else:
            _split_line = line.split('[')
            key = _split_line[0].replace('\t', '').replace(': ', '')
            if not(key in INVERTED_GRAPH_TYPES and INVERTED_GRAPH_TYPES[key] == graph_type):
                continue
            value_str = _split_line[1].replace(']', '')
            values = [float(b.strip()) for b in value_str.split(',')]
            if modifier == '':
                y_axis.append(values[0])
            elif modifier in ('l', 'r'):
                y_axis.append(values[('l','r').index(modifier)])
            elif modifier in ('fl', 'fr', 'bl', 'br'):
                y_axis.append(values[('fl', 'fr', 'bl', 'br').index(modifier)])

plt.plot(timestamps, y_axis, 'r-')
plt.title(GRAPH_TYPES[graph_type])
plt.xlabel(X_AXIS_LABEL)
plt.ylabel(Y_AXIS_LABELS[graph_type])
save = input('Would you like to save the graph? ')
if save == 'yes' or save == 'y':
    plt.savefig('{}.png'.format(GRAPH_TYPES[graph_type]))
plt.show()
