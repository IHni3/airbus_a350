import glob, os;
import json;
import re;

output = {
        "body": {
            "fields": "",
            "constructor": "",
            "build": "",
            "receive": ""
        },
        "wing": {
            "fields": "",
            "constructor": "",
            "build": "",
            "receive": ""
        }
    }

with open('receiver.template', 'r') as t_file:
    receiver_template = t_file.read()

for filename in glob.iglob('_configs/**'):
    if os.path.isfile(filename):
        with open(filename, 'r') as config_file:
            event = False
            gui = False
            sections = []
            counts = []
            gui_options = []
            for line in config_file.readlines():
                if not line.strip():
                    continue
                if line.startswith('class name'):
                    line = line[line.index(':')+1 : ].strip()
                    class_name = line
                    class_name_smol = class_name[0].lower() + class_name[1:]

                if line.startswith('section'):
                    line = line[line.index(":")+1 : ]
                    sections = list(map(str.strip, line.split(',')))
                    for s in sections:
                        out = output[s.lower()]
                        out['fields'] += "    private ArrayList<Object> " + class_name_smol + "PortList;\n"
                        out['constructor'] += "        " + class_name_smol + "PortList = new ArrayList<>();\n"
                        out['build'] += "        for (int i = 0; i < Configuration.instance.numberOf" + class_name
                        if len(sections) > 1:
                            out['build'] += s
                        out['build'] += "; i++) {\n            " + class_name_smol + "PortList.add(" + class_name + "Factory.build());\n        }\n"
                        out['receive'] += '    // --- ' + class_name + ' ' + '-' * (107 - len(class_name)) + "\n\n"
                    continue

                if line.startswith('primary flight display'):
                    gui = True
                    line = line[line.index(":")+1 : ]
                if line.startswith('event'):
                    gui = False
                    event = True
                    line = line[line.index(":")+1 : ]

                if gui:
                    gui_options.append(list(map(str.strip, line.replace('=', ':').split(':'))))

                if event:
                    line = line.strip()
                    if line.endswith(')'):
                        print('Encountered complex event ' + line + '. This needs to be fixed manually!')
                        line = line[0:line.index('(')]
                    line_lower = line[0].lower() + line[1:]
                    print("Current Event \"" + line + "\" for component \"" + class_name + "\", - = " + line_lower)
                    method = input("Enter method name: ")
                    if method == '-':
                        method = line_lower
                    if method:
                        result_name = input("Method result name: ")
                        if len(gui_options) > 1:
                            print('Available gui configs:')
                            i = 0
                            for option in gui_options:
                                print('[' + str(i) + '] ' + option[1] + ' : ' + option[0])
                                i += 1
                            while True:
                                opt = input('Enter gui option index: ')
                                try:
                                    opt = int(opt)
                                    break
                                except ValueError as e:
                                    continue
                        else:
                            opt = 0
                            print('Assumed ' + gui_options[0][0])
                        gui_option = gui_options[opt]
                        for s in sections:
                            event = line
                            event_lower = event[0].lower() + event[1:]
                            out = output[s.lower()]
                            text = receiver_template
                            text = text.replace('${method}', method)
                            text = text.replace('${event}', event)
                            text = text.replace('${event_lower}', event_lower)
                            text = text.replace('${section}', s)
                            if len(sections) > 1:
                                text = text.replace('${section_unique}', s)
                            else:
                                text = text.replace('${section_unique}', '');
                            text = text.replace('${class_name}', class_name)
                            text = text.replace('${class_name_lower}', class_name_smol)
                            text = text.replace('${gui_config}', gui_option[0])
                            text = text.replace('${method_return}', gui_option[1])
                            text = text.replace('${result}', result_name)
                            out['receive'] += text
                    else:
                        for s in sections:
                            out = output[s.lower()]
                            line_lower = line[0].lower() + line[1:]
                            out['receive'] += '    @Subscribe\n' + \
                                    '    public void receive(' + line + ' ' + line_lower + ') {\n' + \
                                    '        FlightRecorder.instance.insert("' + s + '", "receive("' + \
                                    line_lower + '.toString() + ")");\n    }\n\n'
                        

            for s in sections:
                out = output[s.lower()]
                out['receive'] += '    // ' + '-' * 112 + "\n\n"
                
for section, obj in output.items():
    for key, value in obj.items():
        with open(section + "_" + key + ".out", "w") as out_file:
            out_file.write(value)
